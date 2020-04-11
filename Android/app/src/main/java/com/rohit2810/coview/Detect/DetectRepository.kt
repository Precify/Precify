package com.rohit2810.coview.Detect

class DetectRepository(val detectApiService: DetectApiService) {

    suspend fun getAllPosts() = detectApiService.getAllPosts()

    suspend fun addPost(detectPost: DetectPost) = detectApiService.addPost(detectPost)

}