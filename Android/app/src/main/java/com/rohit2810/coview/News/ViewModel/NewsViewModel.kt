package com.rohit2810.coview.News.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rohit2810.coview.MainDatabase
import com.rohit2810.coview.NetworkUtils.NoConnectivityException
import com.rohit2810.coview.News.Database.NewsDao
import com.rohit2810.coview.News.Model.Article
import com.rohit2810.coview.News.Network.NewsApiService
import timber.log.Timber

public class NewsViewModel(application: Application) : AndroidViewModel(application) {
    //News
    private val repository: NewsRepository
    private val newsApiService: NewsApiService
    private val newsDatabase : MainDatabase
    private val newsDao : NewsDao

    init {
        //News
        newsApiService = NewsApiService(application.applicationContext)

        newsDatabase = MainDatabase.getDatabase(application.applicationContext)
        newsDao = newsDatabase.NewsDao()

        repository = NewsRepository(newsApiService, newsDao, viewModelScope)
    }

    //Get all the news
    val allNews : LiveData<List<Article>> = repository.getNews("corona", "in")

}