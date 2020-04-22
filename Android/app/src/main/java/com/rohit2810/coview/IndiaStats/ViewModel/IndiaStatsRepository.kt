package com.rohit2810.coview.IndiaStats.ViewModel

import android.content.Context
import android.widget.Toast
import com.rohit2810.coview.IndiaStats.Database.IndiaStatDao
import com.rohit2810.coview.IndiaStats.Model.IndiaStatsResponse
import com.rohit2810.coview.IndiaStats.Network.IndiaStatsApiService
import com.rohit2810.coview.NetworkUtils.NoConnectivityException
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

class IndiaStatsRepository(
    private val statsApiService: IndiaStatsApiService,
    private val indiaStatDao: IndiaStatDao,
    private val context: Context
) {

    private var isFetched : Boolean = false

    suspend fun insertStats() {
        if(!isFetched) {
            try {
                val response = statsApiService.getStats()
                if (response.isSuccessful) {
                    Timber.d(response.body().toString())
                    isFetched = true
                    response.body()?.let {
                        indiaStatDao.insertStats(it.data.summary)
                    }
                    for (item in response.body()!!.data.regional) {
                        indiaStatDao.insertRegionals(item)
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

    suspend fun getStats() : Response<IndiaStatsResponse> {
        return statsApiService.getStats()
    }
}