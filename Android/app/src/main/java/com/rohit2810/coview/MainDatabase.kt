package com.rohit2810.coview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rohit2810.coview.IndiaStats.Database.IndiaStatDao
import com.rohit2810.coview.IndiaStats.Model.Regional
import com.rohit2810.coview.IndiaStats.Model.Summary
import com.rohit2810.coview.News.Database.NewsDao
import com.rohit2810.coview.News.Model.Article
import com.rohit2810.coview.Stats.Model.Stats
import com.rohit2810.coview.Stats.Database.StatDao
import com.rohit2810.coview.Stats.Model.CountryResponseItem

@Database(
    entities = arrayOf(Stats::class, Article::class, Summary::class, Regional::class, CountryResponseItem::class),
    version = 1,
    exportSchema = false)
public abstract class MainDatabase : RoomDatabase() {

    abstract fun StatDao(): StatDao

    abstract fun NewsDao(): NewsDao

    abstract fun IndiaStatDao() : IndiaStatDao

    companion object {
            @Volatile
            private var Instance: MainDatabase? = null
            fun getDatabase(context: Context): MainDatabase{
                    val tempInstance = Instance
                    if(tempInstance != null) {
                        return tempInstance
                    }
                    synchronized(this){
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            MainDatabase::class.java,
                            "Coview Database"
                        )
                            .build()
                        Instance = instance
                        return instance
                    }
            }
    }


}