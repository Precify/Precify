package com.rohit2810.coview.Stats.Model


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("active")
    val active: Int,
    @SerializedName("affectedCountries")
    val affectedCountries: Int,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int
)