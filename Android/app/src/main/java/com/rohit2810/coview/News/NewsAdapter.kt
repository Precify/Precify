package com.rohit2810.coview.News

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rohit2810.coview.News.Model.Article
import com.rohit2810.coview.News.Model.Source
import com.rohit2810.coview.R

class NewsAdapter internal constructor(context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    val context: Context = context



    private var articles: List<Article> = emptyList()
//    private var sources = emptyList<Source>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImageView : ImageView= itemView.findViewById(R.id.news_item_imageview)
        var sourceTextView: TextView = itemView.findViewById(R.id.news_source_tv)
        var titleTextView : TextView = itemView.findViewById(R.id.news_item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         Glide.with(context).load(articles.get(position).urlToImage).centerCrop().into(holder.newsImageView)
         holder.sourceTextView.text = articles.get(position).source.name
         holder.titleTextView.text = articles.get(position).title
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    internal fun setArray(articles: List<Article>) {
        if(articles.isNotEmpty()) {
            this.articles = articles
        }
//        this.sources = sources
        notifyDataSetChanged()
    }






}