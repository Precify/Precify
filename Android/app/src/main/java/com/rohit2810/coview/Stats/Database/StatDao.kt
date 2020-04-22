package com.rohit2810.coview.Stats.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rohit2810.coview.Stats.Model.CountryResponseItem
import com.rohit2810.coview.Stats.Model.Stats
import retrofit2.http.DELETE

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: Stats)

    @Query("DELETE FROM global_stats_table")
    suspend fun deleteStats()

    @Query("SELECT * FROM global_stats_table")
    fun getStats() : LiveData<List<Stats>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryStats(countryResponseItem: CountryResponseItem)

    @Query("SELECT * FROM global_country_table")
    fun getCountryStats() : LiveData<List<CountryResponseItem>>

}