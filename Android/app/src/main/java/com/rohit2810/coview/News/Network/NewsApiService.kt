package com.rohit2810.coview.News.Network

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.rohit2810.coview.NetworkUtils.NetworkConnectionInterceptor
import com.rohit2810.coview.News.Model.NewsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("q") topic: String,
        @Query("country") country: String
    ): Response<NewsResponse>


    companion object{
        operator fun invoke(context: Context): NewsApiService {


            val requestInterceptor = Interceptor {chain ->
                Timber.d(chain.request().toString())

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey", "8702ccf0af9847b3854170a2b032f72f")
                    .build()


                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                Timber.d( request.toString())


                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(NetworkConnectionInterceptor(context))
                .build()


            val gson = GsonBuilder().setLenient().create()
            val path = "http://newsapi.org/v2/"
            return Retrofit.Builder()
                .baseUrl(path)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }
}