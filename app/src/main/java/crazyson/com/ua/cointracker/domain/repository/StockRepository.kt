package crazyson.com.ua.cointracker.domain.repository

import crazyson.com.ua.cointracker.domain.model.CompanyInfo
import crazyson.com.ua.cointracker.domain.model.CompanyListing
import crazyson.com.ua.cointracker.domain.model.IntradayInfo
import crazyson.com.ua.cointracker.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}