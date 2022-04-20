package crazyson.com.ua.cointracker.data.repository

import crazyson.com.ua.cointracker.data.csv.CompanyListingsParser
import crazyson.com.ua.cointracker.data.csv.CsvParser
import crazyson.com.ua.cointracker.data.local.CompanyListingEntity
import crazyson.com.ua.cointracker.data.local.StockDatabase
import crazyson.com.ua.cointracker.data.mapper.toCompanyListing
import crazyson.com.ua.cointracker.data.mapper.toCompanyListingEntity
import crazyson.com.ua.cointracker.data.remote.StockApi
import crazyson.com.ua.cointracker.domain.model.CompanyListing
import crazyson.com.ua.cointracker.domain.repository.StockRepository
import crazyson.com.ua.cointracker.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CsvParser<CompanyListing>
): StockRepository {

    private val dao = db.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't have been loaded"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't have been loaded via internet"))
                null
            }

            remoteListings.let { listings ->
                dao.clearCompanyListings()
                if (listings != null) {
                    dao.insertCompanyListings(
                        listings.map { it.toCompanyListingEntity() }
                    )
                }
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))

            }
        }
    }
}