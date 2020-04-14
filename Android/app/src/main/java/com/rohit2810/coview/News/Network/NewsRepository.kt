package com.rohit2810.coview.News.Network

import com.rohit2810.coview.News.Model.NewsResponse
import retrofit2.HttpException
import retrofit2.Response

class NewsRepository(val newsApiService: NewsApiService) {

    suspend fun getNews(topic: String, country: String) : Response<NewsResponse> {
        try {
            return newsApiService.getNews(topic, country)
        }catch (e: HttpException) {
            return Response.error(e.code(), e.response()?.errorBody())
        }
    }

}