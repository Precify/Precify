package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rohit2810.coview.News.Model.Article

import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.fragment_news_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsDetailFragment : Fragment() {

    private lateinit var article: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = arguments!!.let { it.get("article") as Article}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view)
            .load(article.urlToImage)
            .centerCrop()
            .into(news_detail_image)

        news_detail_title.text = article.title
        news_detail_desc.text = article.description
        news_detail_link.text = article.url
        news_detail_published.text = article.publishedAt
        news_detail_source.text = article.source.name



    }


}
