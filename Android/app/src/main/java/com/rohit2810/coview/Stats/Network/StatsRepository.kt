package com.rohit2810.coview.Stats.Network

class StatsRepository(val statsApiService: StatsApiService) {

    suspend fun getStats() = statsApiService.getStats()

}