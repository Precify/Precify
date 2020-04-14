package com.rohit2810.coview.Stats.Network

import android.content.Context
import com.rohit2810.coview.NetworkUtils.NetworkConnectionInterceptor
import com.rohit2810.coview.Stats.Model.Stats
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface StatsApiService {
//    @GET("free-api")
//    suspend fun getStats(
//        @Query("global") topic: String = "stats"
//    ): Response<StatsResponse>

    @Headers("Content-Type: application/json")
    @GET("all")
    suspend fun getStats(
    ) : Response<Stats>


    companion object{
        operator fun invoke(context: Context): StatsApiService {

            val okHttpClient = OkHttpClient.Builder().addInterceptor(NetworkConnectionInterceptor(context)).build()


//            val path = "https://api.thevirustracker.com/"
            val path = "https://corona.lmao.ninja/v2/"
            return Retrofit.Builder()
                .baseUrl(path)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StatsApiService::class.java)
        }
    }
}