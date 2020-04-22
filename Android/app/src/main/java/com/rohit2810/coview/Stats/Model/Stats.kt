package com.rohit2810.coview.Stats.Model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "global_stats_table")
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
) {
    @PrimaryKey
    var id : Int = 0;

    public fun setID(id : Int) {
        this.id = id
    }
}