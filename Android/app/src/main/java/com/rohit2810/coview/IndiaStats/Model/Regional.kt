package com.rohit2810.coview.IndiaStats.Model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "regional_stats_table")
data class Regional(

    @SerializedName("confirmedCasesForeign")
    val confirmedCasesForeign: Int,
    @SerializedName("confirmedCasesIndian")
    val confirmedCasesIndian: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("discharged")
    val discharged: Int,
    @PrimaryKey
    @SerializedName("loc")
    val loc: String,
    @SerializedName("totalConfirmed")
    val totalConfirmed: Int
)