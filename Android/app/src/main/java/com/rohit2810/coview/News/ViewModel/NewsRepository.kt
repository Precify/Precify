package com.rohit2810.coview.News.ViewModel

import androidx.lifecycle.LiveData
import com.rohit2810.coview.NetworkUtils.NoConnectivityException
import com.rohit2810.coview.News.Database.NewsDao
import com.rohit2810.coview.News.Model.Article
import com.rohit2810.coview.News.Model.NewsResponse
import com.rohit2810.coview.News.Model.Source
import com.rohit2810.coview.News.Network.NewsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

class NewsRepository(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao,
    private val scope: CoroutineScope
) {

    private var isFetched : Boolean = false

     fun getNews(topic: String, country: String) : LiveData<List<Article>> {
        insertNewsArticles(topic, country)
        return newsDao.getAllNews()
    }

    fun insertNewsArticles(topic: String, country: String) {
        if(!isFetched) {
            scope.launch {
                Timber.d("Article Entered")
                try {
                    val response = newsApiService.getNews(topic, country)
                    if (response.isSuccessful) {
                        Timber.d(response.body().toString())
                        newsDao.deleteAllNews()
                        for (article in response.body()!!.articles) {
                            Timber.d(article.toString())
                            newsDao.insertNews(article)
                        }
                    }
                } catch (e: NoConnectivityException) {

                } catch (e: HttpException) {

                }
            }
        }
    }

}