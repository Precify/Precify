package com.rohit2810.coview.Detect

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*
import timber.log.Timber
import java.util.*

interface DetectApiService {

    @POST("addPost")
    suspend fun addPost(
        @Body detectPost: DetectPost
    ) : Response<Double>

    @GET("getAllPosts")
    suspend fun getAllPosts(): Response<List<DetectPost>>

    companion object {
         operator fun invoke(): DetectApiService {
            val path = "http://5182c18f.ngrok.io/"
             Timber.d("Inside Detect Api Service")
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
    }
}