package com.rohit2810.coview.Fragments


import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    internal var pStatus = 0
    internal lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val res = resources
        val drawable = res.getDrawable(R.drawable.circular_progress_bar)
        val mProgress = view.findViewById<View>(R.id.circularProgressbar) as ProgressBar
        mProgress.progress = 0   // Main Progress
        mProgress.secondaryProgress = 100 // Secondary Progress
        mProgress.max = 100 // Maximum Progress
        mProgress.progressDrawable = drawable
        tv = view.findViewById<View>(R.id.tv) as TextView

        val value : Int = arguments?.getInt("value", 0) ?: 0
        tv.text = value.toString() + "%"
        if(!arguments?.getString("url").isNullOrEmpty()) {
            home_url_tv.visibility = View.VISIBLE
            home_link_tv.visibility = View.VISIBLE
            home_link_tv.text = arguments?.getString("url", "new") ?: "New"
        }
        home_perc_tv.text = "The link or Article is " + tv.text.toString() + " real."
        val animation = ObjectAnimator.ofInt(mProgress, "progress", 0, value);
        animation.setDuration(3000);
        animation.setInterpolator(DecelerateInterpolator());
        animation.start();

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).nav_view.visibility  = View.VISIBLE
    }

}
