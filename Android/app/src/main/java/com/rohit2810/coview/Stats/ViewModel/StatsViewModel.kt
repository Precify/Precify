package com.rohit2810.coview.Stats.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.rohit2810.coview.MainDatabase
import com.rohit2810.coview.Stats.Database.StatDao
import com.rohit2810.coview.Stats.Model.CountryResponseItem
import com.rohit2810.coview.Stats.Model.Stats
import com.rohit2810.coview.Stats.Network.StatsApiService
import kotlinx.coroutines.launch

class StatsViewModel(application: Application) : AndroidViewModel(application) {
    //MainDatabase
    private val mainDatabase: MainDatabase
    private val statDao: StatDao

    //Stats
    private val statsApiService: StatsApiService
    private val statsRepository : StatsRepository





    init {
        //Stats
        statsApiService = StatsApiService(application.applicationContext)


        //MainDatabase
        mainDatabase = MainDatabase.getDatabase(application.applicationContext)
        statDao = mainDatabase.StatDao()
        statsRepository = StatsRepository(
            statsApiService,
            statDao,
            application.applicationContext
        )
    }

    fun getStats() : LiveData<List<Stats>> {
        viewModelScope.launch {
            statsRepository.insertStats()
        }
        return statDao.getStats()
    }

    fun getCountryStats(): LiveData<List<CountryResponseItem>> {
        viewModelScope.launch {
            statsRepository.insertCountryStats()
        }
        return statDao.getCountryStats()
    }



}