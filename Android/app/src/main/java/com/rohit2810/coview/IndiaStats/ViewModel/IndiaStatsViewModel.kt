package com.rohit2810.coview.IndiaStats.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rohit2810.coview.IndiaStats.Database.IndiaStatDao
import com.rohit2810.coview.IndiaStats.Model.IndiaStatsResponse
import com.rohit2810.coview.IndiaStats.Model.Regional
import com.rohit2810.coview.IndiaStats.Model.Summary
import com.rohit2810.coview.IndiaStats.Network.IndiaStatsApiService
import com.rohit2810.coview.MainDatabase
import com.rohit2810.coview.NetworkUtils.NoConnectivityException
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class IndiaStatsViewModel(application: Application) : AndroidViewModel(application) {
    //MainDatabase
    private val mainDatabase: MainDatabase

    //Stats
    private val indiaStatsApiService : IndiaStatsApiService
    private val indiaStatsRepository : IndiaStatsRepository
    private val indiaStatDao : IndiaStatDao

    public var position : Int = 0

    fun setValue(pos: Int) {
        position = pos
    }

    init {
        //Stats
        indiaStatsApiService = IndiaStatsApiService.invoke(application.applicationContext)


        //MainDatabase
        mainDatabase = MainDatabase.getDatabase(application.applicationContext)
        indiaStatDao = mainDatabase.IndiaStatDao()
        indiaStatsRepository = IndiaStatsRepository(
            indiaStatsApiService,
            indiaStatDao,
            application.applicationContext
        )
    }

    val getNewStats : LiveData<IndiaStatsResponse?> = liveData {
        try {
            val response = indiaStatsRepository.getStats()
            if(response.isSuccessful) {
                Timber.d(response.body().toString())
                emit(response.body())
            }
        }catch (e : NoConnectivityException) {

        }catch (e : HttpException) {

        }

    }

    fun getRegions() : LiveData<List<Regional>> {
        viewModelScope.launch {
            indiaStatsRepository.insertStats()
        }
        return indiaStatDao.getRegions()
    }


    fun getStats() : LiveData<List<Summary>> {
        viewModelScope.launch {
            indiaStatsRepository.insertStats()
        }
        return indiaStatDao.getStats()
    }




}