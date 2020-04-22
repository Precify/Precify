package com.rohit2810.coview.News.Model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "news_article_table")
@SuppressLint("ParcelCreator")
@Parcelize
data class Article(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @Embedded(prefix = "source_")
    val source: Source,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null
): Parcelable {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    fun setId(id: Int) {
        this.id = id
    }

    fun getId() : Int {
        return id
    }
}