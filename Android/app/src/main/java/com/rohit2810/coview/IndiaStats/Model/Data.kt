package com.rohit2810.coview.IndiaStats.Model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("regional")
    val regional: List<Regional>,
    @SerializedName("summary")
    val summary: Summary
)