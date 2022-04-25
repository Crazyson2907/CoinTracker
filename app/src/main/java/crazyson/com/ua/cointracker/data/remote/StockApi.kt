package crazyson.com.ua.cointracker.data.remote

import crazyson.com.ua.cointracker.data.remote.dto.CompanyInfoDto
import retrofit2.http.Query
import okhttp3.ResponseBody
import retrofit2.http.GET

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyInfoDto

    companion object {
        const val API_KEY = "C87EWVC94FZKRXLT"
        const val BASE_URL = "https://alphavantage.co"
    }
}