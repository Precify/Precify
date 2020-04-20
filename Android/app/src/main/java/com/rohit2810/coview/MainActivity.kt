package com.rohit2810.coview

import android.content.Intent
import android.os.Bundle
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
import timber.log.Timber

class MainActivity : AppCompatActivity() {

//    private lateinit var detectPostViewModel: DetectPostViewModel
    private lateinit var navController: NavController
    lateinit var navView : BottomNavigationView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navView = findViewById(R.id.nav_view)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(MainViewModel::class.java)
//        detectPostViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(DetectPostViewModel::class.java)

        if(intent.action.equals(Intent.ACTION_SEND)) {
            navController.navigate(R.id.splashFragment)
            Timber.d("entered")
            val text : String? = intent.getStringExtra(Intent.EXTRA_TEXT)
            if(!text!!.isNullOrEmpty()) {
                val post = DetectPost(text = text)
                mainViewModel.addPost(post).observe(this, Observer {
                    val value : Int = it!!.times(100).toInt()
                    Timber.d(value.toString())
//                    val newText = "Article: " + text
                    navController.navigate(R.id.homeFragment, bundleOf("value" to value, "url" to text))
                })
            }


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

    override fun onNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onNavigateUp()
    }

}
