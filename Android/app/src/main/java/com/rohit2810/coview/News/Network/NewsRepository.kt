package com.rohit2810.coview.News.Network

class NewsRepository(val newsApiService: NewsApiService) {

    suspend fun getNews(topic: String, country: String) = newsApiService.getNews(topic, country)

}