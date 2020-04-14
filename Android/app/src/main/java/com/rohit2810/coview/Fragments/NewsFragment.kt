package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rohit2810.coview.MainViewModel
import com.rohit2810.coview.News.Model.Article
import com.rohit2810.coview.News.NewsAdapter
import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.fragment_news.*
import timber.log.Timber
import java.lang.Exception


class NewsFragment : Fragment(){

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: MainViewModel
    private lateinit var navController: NavController

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

        newsViewModel = activity?.run {
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        newsAdapter = NewsAdapter(view.context, {article -> newsArticleClicked(article)})
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

    private fun newsArticleClicked(article: Article) {
//        Toast.makeText(context, article.toString(), Toast.LENGTH_SHORT).show()
        Timber.d(article.content)
        navController.navigate(
            R.id.action_newsFragment_to_newsDetailFragment,
            bundleOf("article" to article)
        )
    }
}
