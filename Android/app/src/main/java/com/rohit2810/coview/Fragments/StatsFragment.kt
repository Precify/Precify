package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_stats.*

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.rohit2810.coview.R
import com.rohit2810.coview.Stats.ViewModel.StatsViewModel


class StatsFragment : Fragment() {

    //    private lateinit var statsViewModel: StatsViewModel
    private lateinit var statsViewModel: StatsViewModel
//    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stats_main.visibility = View.GONE
        stats_progressBar.visibility = View.VISIBLE
        val anyChartView: PieChart = view.findViewById(R.id.any_chart_view)
        anyChartView.setHoleRadius(40f);
        anyChartView.setTransparentCircleAlpha(0);
        anyChartView.setCenterText("Covid 19 Overview");
        anyChartView.setCenterTextSize(14f)
        var isStart = true


        statsViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(activity!!.application)
            .create(StatsViewModel::class.java)

            statsViewModel.getStats().observe(this, Observer {
                if(it!!.isNotEmpty()) {
                    stats_progressBar.visibility = View.GONE
                    stats_main.visibility = View.VISIBLE
                    stats_total_active_cases.text = it[0]!!.active.toString()
                    stats_total_cases.text = it[0].cases.toString()
                    stats_total_recovered_cases.text = it[0].recovered.toString()
                    stats_total_death_cases.text = it[0].deaths.toString()

                    val data = arrayListOf<PieEntry>()
                    data.add(PieEntry(it[0].active.toFloat(), "Active cases"))
                    data.add(PieEntry(it[0].recovered.toFloat(), "Recovered"))
                    data.add(PieEntry(it[0].deaths.toFloat(), "Deaths"))
//            data[0].value =


                    val pieDataSet = PieDataSet(data, "Covid 19 stats");
                    val colors = arrayListOf<Int>()
                    colors.add(Color.parseColor("#EE6352"))
                    colors.add(Color.parseColor("#FF7D83"))
                    colors.add(Color.parseColor("#FF9CB4"))
                    pieDataSet.setColors(colors)


                    val pieData = PieData(pieDataSet)
                    if (isStart) {
                        isStart = false
                        anyChartView.animateY(1400, Easing.EaseInOutQuad)
                    }
                    anyChartView.setData(pieData)
                    anyChartView.invalidate()
                }

            })

    }

    override fun onDetach() {
        super.onDetach()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onStop() {
        super.onStop()

    }

    override fun onPause() {
        super.onPause()

    }


}
