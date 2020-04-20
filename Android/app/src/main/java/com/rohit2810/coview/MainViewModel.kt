package com.rohit2810.coview

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rohit2810.coview.Detect.DetectPost
import com.rohit2810.coview.Detect.Network.DetectApiService
import com.rohit2810.coview.Detect.Network.DetectRepository
import com.rohit2810.coview.NetworkUtils.NoConnectivityException
import com.rohit2810.coview.News.Model.NewsResponse
import com.rohit2810.coview.News.Network.NewsApiService
import com.rohit2810.coview.News.Network.NewsRepository
import com.rohit2810.coview.Stats.Model.Stats
import com.rohit2810.coview.Stats.Network.StatsApiService
import com.rohit2810.coview.Stats.Network.StatsRepository
import timber.log.Timber

class MainViewModel(application: Application) : AndroidViewModel(application) {
    //Detection
    private val detectApiService : DetectApiService
    private val detectRepository : DetectRepository

    //News
    private val repository: NewsRepository
    private val newsApiService: NewsApiService

    //Stats
    private val statsApiService: StatsApiService
    private val statsRepository : StatsRepository

    init {
        //Detect Post
        detectApiService = DetectApiService(application.applicationContext)
        detectRepository = DetectRepository(detectApiService)

        //News
        newsApiService = NewsApiService(application.applicationContext)
        repository = NewsRepository(newsApiService)

        //Stats
        statsApiService = StatsApiService(application.applicationContext)
        statsRepository = StatsRepository(statsApiService)
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

    //Get all the news
    val allNews : LiveData<NewsResponse?> = liveData {
        try {
            val response = repository.getNews("corona", "in")
            if (response.isSuccessful) {
                Timber.d(response.body().toString())
                emit(response.body())
            } else {
                Log.d("NewActivity", response.message())
            }
        }catch (e : NoConnectivityException) {
            Toast.makeText(getApplication(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    //Get all the stats
    val stats : LiveData<Stats?> = liveData {
        try {
            val response = statsRepository.getStats()
            if(response.isSuccessful) {
                emit(response.body())
            }else {
                Timber.d(response.message())
            }
        }catch (e : NoConnectivityException) {
            Toast.makeText(getApplication(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    }
}