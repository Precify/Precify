package com.rohit2810.coview.Detect.Network

import android.widget.Toast
import com.rohit2810.coview.Detect.DetectPost
import retrofit2.HttpException
import retrofit2.Response

class DetectRepository(val detectApiService: DetectApiService) {

    suspend fun getAllPosts() = detectApiService.getAllPosts()

    suspend fun addPost(detectPost: DetectPost) = detectApiService.addPost(detectPost)

}