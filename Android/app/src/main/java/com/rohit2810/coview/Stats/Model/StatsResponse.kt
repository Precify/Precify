package com.rohit2810.coview.Stats.Model


import com.google.gson.annotations.SerializedName
import com.rohit2810.coview.Stats.Model.Result

data class StatsResponse(
    @SerializedName("results")
    val results: List<Result>
)