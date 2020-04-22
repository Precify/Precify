package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rohit2810.coview.News.Model.Article
import com.rohit2810.coview.News.Adapter.NewsAdapter
import com.rohit2810.coview.News.ViewModel.NewsViewModel
import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.fragment_news.*
import timber.log.Timber


class NewsFragment : Fragment(){

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var navController: NavController
    private lateinit var article: Article

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

        navController = Navigation.findNavController(view)
        val mainNewsTitle = view.findViewById<TextView>(R.id.main_news_title)

        newsViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(NewsViewModel::class.java)
        newsAdapter =
            NewsAdapter(view.context, { article -> newsArticleClicked(article) })
        newsViewModel.allNews.observe(this, Observer {
            if(it!!.isNotEmpty()) {
                news_progressBar.visibility = View.GONE
                main_news.visibility = View.VISIBLE
                article = it[0]
                Timber.d(article.toString())
                Glide.with(view).load(article!!.urlToImage).into(main_news_image)
                mainNewsTitle.text = it[0].title
                val newList = it.drop(1)
                if(newList.isNotEmpty()) {
                    newsAdapter.setArray(newList)
                }
            }
        })
        main_news_image.setOnClickListener {
            navController.navigate(
                R.id.action_newsFragment_to_newsDetailFragment,
                bundleOf("article" to article)
            )
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.main_news_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = newsAdapter
        val dividerItemDecoration = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)




    }

    private fun newsArticleClicked(article: Article) {
//        Toast.makeText(context, article.toString(), Toast.LENGTH_SHORT).show()
        Timber.d(article.content)
        navController.navigate(
            R.id.action_newsFragment_to_newsDetailFragment,
            bundleOf("article" to article)
        )
    }
}
