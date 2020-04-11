package com.rohit2810.coview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.rohit2810.coview.Detect.DetectPost
import com.rohit2810.coview.Detect.DetectPostViewModel
import timber.log.Timber
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private lateinit var detectPostViewModel: DetectPostViewModel
    private lateinit var navController: NavController
    lateinit var navView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navView = findViewById(R.id.nav_view)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        detectPostViewModel = ViewModelProvider(this).get(DetectPostViewModel::class.java)

        if(intent.action.equals(Intent.ACTION_SEND)) {
            navController.navigate(R.id.splashFragment)
            Timber.d("entered")
            val text : String? = intent.getStringExtra(Intent.EXTRA_TEXT)
            if(!text!!.isNullOrEmpty()) {
                val post = DetectPost(text = text)
                detectPostViewModel.addPost(post).observe(this, Observer {
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
                detectPostViewModel.addPost(post).observe(this, Observer {
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
