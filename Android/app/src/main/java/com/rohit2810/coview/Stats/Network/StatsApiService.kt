package com.rohit2810.coview.Stats.Network

import android.util.Log
import com.rohit2810.coview.News.Model.NewsResponse
import com.rohit2810.coview.News.Network.NewsApiService
import com.rohit2810.coview.Stats.Model.StatsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StatsApiService {
    @GET("free-api")
    suspend fun getStats(
        @Query("global") topic: String = "stats"
    ): Response<StatsResponse>


    companion object{
        operator fun invoke(): StatsApiService {


            val path = "https://api.thevirustracker.com/"
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StatsApiService::class.java)
        }
    }
}