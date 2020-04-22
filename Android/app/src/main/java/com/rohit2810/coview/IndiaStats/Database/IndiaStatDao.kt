package com.rohit2810.coview.IndiaStats.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rohit2810.coview.IndiaStats.Model.Regional
import com.rohit2810.coview.IndiaStats.Model.Summary
import com.rohit2810.coview.Stats.Model.Stats

@Dao
interface IndiaStatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(summary: Summary)

    @Query("DELETE FROM india_stats_table")
    suspend fun deleteStats()

    @Query("SELECT * FROM india_stats_table")
    fun getStats() : LiveData<List<Summary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegionals(region : Regional)

    @Query("SELECT * FROM regional_stats_table ORDER BY totalConfirmed DESC")
    fun getRegions() : LiveData<List<Regional>>

}