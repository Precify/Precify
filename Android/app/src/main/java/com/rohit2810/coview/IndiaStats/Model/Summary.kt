package com.rohit2810.coview.IndiaStats.Model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "india_stats_table")
data class Summary(
    @SerializedName("confirmedButLocationUnidentified")
    val confirmedButLocationUnidentified: Int,
    @SerializedName("confirmedCasesForeign")
    val confirmedCasesForeign: Int,
    @SerializedName("confirmedCasesIndian")
    val confirmedCasesIndian: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("discharged")
    val discharged: Int,
    @SerializedName("total")
    val total: Int
) {
    @PrimaryKey
    var id : Int = 0

    public fun setID(id : Int) {
        this.id = id
    }
}