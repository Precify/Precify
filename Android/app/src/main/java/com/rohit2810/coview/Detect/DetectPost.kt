package com.rohit2810.coview.Detect


import com.google.gson.annotations.SerializedName

data class DetectPost(
    @SerializedName("author")
    val author: String =" ",
    @SerializedName("date")
    val date: String = " ",
    @SerializedName("name")
    val name: String = " ",
    @SerializedName("source")
    val source: String = " ",
    @SerializedName("text")
    val text: String = " ",
    @SerializedName("url")
    val url: String = " "
)