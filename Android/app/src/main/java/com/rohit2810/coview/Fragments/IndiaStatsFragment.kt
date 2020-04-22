package com.rohit2810.coview.Fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.rohit2810.coview.IndiaStats.ViewModel.IndiaStatsViewModel
import com.rohit2810.coview.IndiaStats.Model.Summary

import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.fragment_stats.*

class IndiaStatsFragment : Fragment() {

    private lateinit var statsViewModel: IndiaStatsViewModel


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

        val anyChartView: PieChart = view?.findViewById(R.id.any_chart_view)
        anyChartView.setHoleRadius(40f);
        anyChartView.setTransparentCircleAlpha(0);
        anyChartView.setCenterText("Covid 19 Overview");
        anyChartView.setCenterTextSize(14f)
        var isStart = true
//        navController = Navigation.findNavController(view)


        activity?.let {
            statsViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(activity!!.application)
                .create(IndiaStatsViewModel::class.java)

            statsViewModel.getStats().observe(this, Observer {
                //            if(it!!.isNotEmpty()) {
                if(it.isNotEmpty()) {
                    var stats: Summary = it.get(0)
                    stats_progressBar.visibility = View.GONE
                    stats_main.visibility = View.VISIBLE
                    stats_total_active_cases.text = (stats.total - stats.discharged - stats.deaths).toString()
                    stats_total_cases.text = stats.total.toString()
                    stats_total_recovered_cases.text = stats.discharged.toString()
                    stats_total_death_cases.text = stats.deaths.toString()

                    val data = arrayListOf<PieEntry>()
                    data.add(PieEntry((stats.total - stats.discharged - stats.deaths).toFloat(), "Active cases"))
                    data.add(PieEntry(stats.discharged.toFloat(), "Recovered"))
                    data.add(PieEntry(stats.deaths.toFloat(), "Deaths"))
//            data[0].value =


                    val pieDataSet = PieDataSet(data, "Employee Sales");
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
//            }
                }
            })
        }


    }

}
