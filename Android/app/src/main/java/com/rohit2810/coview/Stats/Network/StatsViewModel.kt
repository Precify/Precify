package com.rohit2810.coview.Stats.Network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rohit2810.coview.Stats.Model.StatsResponse

class StatsViewModel : ViewModel() {
    private val statsApiService: StatsApiService
    private val statsRepository : StatsRepository

    init {
        statsApiService = StatsApiService()
        statsRepository = StatsRepository(statsApiService)
    }

    val stats : LiveData<StatsResponse?> = liveData {
        val response = statsRepository.getStats()
        if(response.isSuccessful) {
            emit(response.body())
        }else {
            Log.d("NewActivity", response.message())
        }
    }
}