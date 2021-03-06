package com.rohit2810.coview.News.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rohit2810.coview.News.Model.Article

@Dao
interface NewsDao {

    @Insert
    suspend fun insertNews(article: Article)

    @Query("DELETE FROM news_article_table")
    suspend fun deleteAllNews()

    @Query("SELECT * FROM news_article_table")
    fun getAllNews() : LiveData<List<Article>>
}