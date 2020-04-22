package com.rohit2810.coview.IndiaStats.Network

import android.content.Context
import com.rohit2810.coview.IndiaStats.Model.IndiaStatsResponse
import com.rohit2810.coview.NetworkUtils.NetworkConnectionInterceptor
import com.rohit2810.coview.Stats.Model.Stats
import com.rohit2810.coview.Stats.Network.StatsApiService
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface IndiaStatsApiService {
    @Headers("Content-Type: application/json")
    @GET("latest")
    suspend fun getStats(
    ) : Response<IndiaStatsResponse>


    companion object{
        operator fun invoke(context: Context): IndiaStatsApiService {

            val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(NetworkConnectionInterceptor(context))
                .build()


            val path = "https://api.rootnet.in/covid19-in/stats/"
            return Retrofit.Builder()
                .baseUrl(path)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IndiaStatsApiService::class.java)
        }
    }
}