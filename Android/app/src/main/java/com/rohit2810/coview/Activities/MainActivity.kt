package com.rohit2810.coview.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.rohit2810.coview.Detect.DetectPost
import com.rohit2810.coview.Detect.DetectViewModel
import com.rohit2810.coview.IndiaStats.ViewModel.IndiaStatsViewModel
import com.rohit2810.coview.R
import com.rohit2810.coview.Stats.ViewModel.StatsViewModel
import kotlinx.coroutines.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

//    private lateinit var detectPostViewModel: DetectPostViewModel
    private lateinit var navController: NavController
    lateinit var navView : BottomNavigationView
    private lateinit var mainViewModel: DetectViewModel
    private lateinit var statsViewModel: IndiaStatsViewModel
    private lateinit var globalStatsViewModel: StatsViewModel
    public  var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        navView = findViewById(R.id.nav_view)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(DetectViewModel::class.java)
        statsViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(IndiaStatsViewModel::class.java)
        globalStatsViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(StatsViewModel::class.java)

        if(intent.action.equals(Intent.ACTION_SEND)) {
            navController.navigate(R.id.splashFragment)
            Timber.d("entered")
            val text : String? = intent.getStringExtra(Intent.EXTRA_TEXT)
            if(!text!!.isNullOrEmpty()) {
                lateinit var post: DetectPost
                if(text?.contains("www")) {
                    post = DetectPost(url = text)
                }else {
                    post = DetectPost(text = text)
                }

                mainViewModel.addPost(post).observe(this, Observer {
                    val value : Int = it!!.times(100).toInt()
                    Timber.d(value.toString())
//                    val newText = "Article: " + text
                    navController.navigate(R.id.homeFragment, bundleOf("value" to value, "url" to text))
                })
            }
//            GlobalScope.launch(context = Dispatchers.Main) {
//                delay(1000)
//                val text = "https://timesofindia.indiatimes.com/india/coronavirus-epidemic-could-peak-in-india-by-mid-may-study/articleshow/75283258.cms"
//                val value = 92
//                navController.navigate(R.id.homeFragment, bundleOf("value" to value, "url" to text))
//            }



        }else if(intent.action.equals(Intent.ACTION_VIEW)) {
            navController.navigate(R.id.splashFragment)
            val text : String? = intent!!.dataString
            Timber.d(text)
            if(!text!!.isNullOrEmpty()) {
                val post = DetectPost(url = text)
                mainViewModel.addPost(post).observe(this, Observer {
                    val value : Int = it!!.times(100).toInt()
                    Timber.d(value.toString())
                    navController.navigate(R.id.homeFragment, bundleOf("value" to value, "url" to text))
                })
            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.main_menu_about -> startActivity(Intent(this, AboutActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}
