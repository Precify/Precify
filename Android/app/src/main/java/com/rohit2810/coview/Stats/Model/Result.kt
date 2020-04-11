package com.rohit2810.coview.Stats.Model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("total_active_cases")
    val totalActiveCases: Int,
    @SerializedName("total_affected_countries")
    val totalAffectedCountries: Int,
    @SerializedName("total_cases")
    val totalCases: Int,
    @SerializedName("total_deaths")
    val totalDeaths: Int,
    @SerializedName("total_new_cases_today")
    val totalNewCasesToday: Int,
    @SerializedName("total_new_deaths_today")
    val totalNewDeathsToday: Int,
    @SerializedName("total_recovered")
    val totalRecovered: Int,
    @SerializedName("total_serious_cases")
    val totalSeriousCases: Int,
    @SerializedName("total_unresolved")
    val totalUnresolved: Int
)