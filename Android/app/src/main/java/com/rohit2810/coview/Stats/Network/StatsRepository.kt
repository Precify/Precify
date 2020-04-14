package com.rohit2810.coview.Stats.Network

import com.rohit2810.coview.Stats.Model.Stats
import retrofit2.Response

class StatsRepository(
    private val statsApiService: StatsApiService
//    private val statDao: StatDao,
//    private val viewModelScope: CoroutineScope,
//    private val context : Context
) {


    suspend fun getStats() : Response<Stats> {
//        refreshStats()
        return statsApiService.getStats()
    }

//    suspend fun refreshStats(){
//        viewModelScope.launch {
//            try{
//                val res = statsApiService.getStats()
//                if(res.isSuccessful) {
//                        res.body()?.let { statDao.insertStats(it) }
//                }
//            }catch (e : HttpException) {
//                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

}