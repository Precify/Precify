package com.rohit2810.coview.IndiaStats.Model


import com.google.gson.annotations.SerializedName
import com.rohit2810.coview.IndiaStats.Model.Data

data class IndiaStatsResponse(
    @SerializedName("data")
    val data: Data
)