package com.rohit2810.coview.Stats.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.rohit2810.coview.NetworkUtils.NoConnectivityException
import com.rohit2810.coview.Stats.Database.StatDao
import com.rohit2810.coview.Stats.Model.CountryResponseItem
import com.rohit2810.coview.Stats.Model.Stats
import com.rohit2810.coview.Stats.Network.StatsApiService
import timber.log.Timber
import java.lang.Exception

class StatsRepository(
    private val statsApiService: StatsApiService,
    private val statDao: StatDao,
    private val context: Context
) {

    private var isFetched : Boolean = false
    private var isCountryFetched: Boolean = false

    suspend fun insertStats() {
        if(!isFetched) {
            try {
                val response = statsApiService.getStats()
                if (response.isSuccessful) {
                    Timber.d(response.body().toString())
                    response.body()?.let {
                        statDao.insertStats(it)
                    }
                }
            } catch (e: NoConnectivityException) {
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Showing previously fetched data", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Timber.d(e.localizedMessage)
            }
        }
    }

    suspend fun insertCountryStats() {
        if(!isCountryFetched) {
            try {
                val res = statsApiService.getCountryStats()
                if (res.isSuccessful) {
                    var count : Int = 0
                    isCountryFetched = true
                    res.body()?.let {
                        for (item in it) {
                            statDao.insertCountryStats(item)
                            count++
                            if(count >= 20) {
                                break
                            }
                        }
                    }
                } else {

                }
            } catch (e: NoConnectivityException) {
                Timber.d(e.localizedMessage)
            } catch (e: Exception) {
                Timber.d(e.localizedMessage)
            }
        }
    }


}