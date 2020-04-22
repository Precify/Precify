package com.rohit2810.coview.Detect

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.rohit2810.coview.Detect.DetectPost
import com.rohit2810.coview.Detect.Network.DetectApiService
import com.rohit2810.coview.Detect.Network.DetectRepository
import com.rohit2810.coview.NetworkUtils.NoConnectivityException
import com.rohit2810.coview.News.Model.Article
import com.rohit2810.coview.News.Network.NewsApiService
import com.rohit2810.coview.News.ViewModel.NewsRepository
import timber.log.Timber

class DetectViewModel(application: Application) : AndroidViewModel(application) {
    //Detection
    private val detectApiService : DetectApiService
    private val detectRepository : DetectRepository



//    //Stats
//    private val statsApiService: StatsApiService
//    private val statsRepository : StatsRepository



    init {
        //Detect Post
        detectApiService = DetectApiService(application.applicationContext)
        detectRepository = DetectRepository(detectApiService)


    }


    //Add the post
    fun addPost(detectPost: DetectPost) : LiveData<Double?> = liveData {
        try {
            val res = detectRepository.addPost(detectPost)
            if(res.isSuccessful) {
                emit(res.body())
            }
        }catch (e: NoConnectivityException) {
            Toast.makeText(getApplication(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }



}