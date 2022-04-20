package crazyson.com.ua.cointracker.data.remote

import retrofit2.http.Query
import okhttp3.ResponseBody
import retrofit2.http.GET

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = "C87EWVC94FZKRXLT"
        const val BASE_URL = "https://alphavantage.co"
    }
}