package crazyson.com.ua.cointracker.di

import crazyson.com.ua.cointracker.data.csv.CompanyListingsParser
import crazyson.com.ua.cointracker.data.csv.CsvParser
import crazyson.com.ua.cointracker.data.csv.IntradayInfoParser
import crazyson.com.ua.cointracker.data.repository.StockRepositoryImpl
import crazyson.com.ua.cointracker.domain.model.CompanyListing
import crazyson.com.ua.cointracker.domain.model.IntradayInfo
import crazyson.com.ua.cointracker.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CsvParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CsvParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}