package com.rohit2810.coview.Detect.Network

import android.content.Context
import android.util.JsonReader
import com.google.gson.GsonBuilder
import com.rohit2810.coview.Detect.DetectPost
import com.rohit2810.coview.NetworkUtils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*
import timber.log.Timber

interface DetectApiService {

    @POST("addPost")
    suspend fun addPost(
        @Body detectPost: DetectPost
    ) : Response<Double>

    @GET("getAllPosts")
    suspend fun getAllPosts(): Response<List<DetectPost>>

    companion object {




         operator fun invoke(context: Context): DetectApiService {
             val mContext = context
             val okHttpClient = OkHttpClient.Builder().addInterceptor(NetworkConnectionInterceptor(mContext))
            val path = "https://stark-anchorage-45962.herokuapp.com/"
             Timber.d("Inside Detect Api Service")
            return Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }

    }
}