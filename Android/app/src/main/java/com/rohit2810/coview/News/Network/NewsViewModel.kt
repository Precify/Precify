package com.rohit2810.coview.News.Network

import android.util.Log
import androidx.lifecycle.*
import com.rohit2810.coview.News.Model.NewsResponse

class NewsViewModel : ViewModel() {

    private val repository: NewsRepository
    private val newsApiService: NewsApiService

    init {
         newsApiService = NewsApiService()
         repository = NewsRepository(newsApiService)
    }


    val allNews : LiveData<NewsResponse?> = liveData {
        val response = repository.getNews("corona", "in")
        if(response.isSuccessful) {
            emit(response.body())
        }else {
            Log.d("NewActivity", response.message())
        }
    }

}