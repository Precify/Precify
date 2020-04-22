package com.rohit2810.coview.Stats.Model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "global_country_table")
data class CountryResponseItem (
    @SerializedName("active")
    val active: Int,
    @SerializedName("cases")
    val cases: Int,
    @PrimaryKey
    @SerializedName("country")
    val country: String,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("tests")
    val tests: Int,
    @SerializedName("todayCases")
    val todayCases: Int,
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @SerializedName("updated")
    val updated: Long
) : Serializable