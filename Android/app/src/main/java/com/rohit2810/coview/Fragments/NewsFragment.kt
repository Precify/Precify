package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rohit2810.coview.News.NewsAdapter
import com.rohit2810.coview.News.Network.NewsViewModel
import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        newsViewModel = ViewModelProvider(this).get(newsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsAdapter = NewsAdapter(view.context)
        newsViewModel.allNews.observe(this, Observer {
            if(it!!.articles.isNotEmpty()) {
                Glide.with(view.context).load(it.articles.get(0).urlToImage).into(main_news_image)
                main_news_title.text = it.articles.get(0).title
                newsAdapter.setArray(it.articles.subList(1, it.articles.lastIndex))
            }
        })

        val recyclerView: RecyclerView = view.findViewById(R.id.main_news_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = newsAdapter
        val dividerItemDecoration = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)


    }

}
