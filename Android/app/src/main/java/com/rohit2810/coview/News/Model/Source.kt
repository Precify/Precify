package com.rohit2810.coview.News.Model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Source(
    @SerializedName("name")
    val name: String
) : Parcelable