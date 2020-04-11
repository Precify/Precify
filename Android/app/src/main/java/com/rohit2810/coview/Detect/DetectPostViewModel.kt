package com.rohit2810.coview.Detect

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class DetectPostViewModel : ViewModel() {
    private val detectApiService : DetectApiService
    private val detectRepository : DetectRepository

    init {
        detectApiService = DetectApiService()
        detectRepository = DetectRepository(detectApiService)
    }

    val allPosts : LiveData<List<DetectPost>?> = liveData {
        val response = detectRepository.getAllPosts()
        if(response.isSuccessful) {
            Log.d("NewActivity", response.body().toString())
            emit(response.body())
        }
    }

    fun addPost(detectPost: DetectPost) : LiveData<Double?> = liveData {
        val res = detectRepository.addPost(detectPost)
        emit(res.body())
    }
}