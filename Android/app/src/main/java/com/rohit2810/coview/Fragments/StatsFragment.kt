package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_stats.*
import com.anychart.enums.LegendLayout
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.AnyChart
import com.anychart.AnyChartView

import com.anychart.enums.Align
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.rohit2810.coview.MainViewModel
import java.lang.Exception
import android.graphics.Color
import com.rohit2810.coview.R


class StatsFragment : Fragment() {

//    private lateinit var statsViewModel: StatsViewModel
    private lateinit var statsViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.rohit2810.coview.R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        statsViewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(StatsViewModel::class.java)
        val anyChartView : PieChart = view.findViewById(com.rohit2810.coview.R.id.any_chart_view)
        anyChartView.setHoleRadius(40f);
        anyChartView.setTransparentCircleAlpha(0);
        anyChartView.setCenterText("Covid 19 Overview");
        anyChartView.setCenterTextSize(14f);




        statsViewModel = activity?.run {
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        statsViewModel.stats.observe(this, Observer {
            stats_total_active_cases.text = it!!.active.toString()
            stats_total_cases.text = it.cases.toString()
            stats_total_recovered_cases.text = it.recovered.toString()
            stats_total_death_cases.text = it.deaths.toString()

                val data = arrayListOf<PieEntry>()
            data.add(PieEntry(it.active.toFloat(), "Active cases"))
            data.add(PieEntry(it.recovered.toFloat(), "Recovered"))
            data.add(PieEntry(it.deaths.toFloat(), "Deaths"))
//            data.add(Entry("Total recovered cases", it.recovered))
//            data.add(ValueDataEntry("Total death cases", it.deaths))

            val pieDataSet = PieDataSet(data, "Employee Sales");
            val colors = arrayListOf<Int>()
            colors.add(Color.parseColor("#EE6352"))
//            colors.add(Color.RED)
            colors.add(Color.parseColor("#FF7D83"))
            colors.add(Color.parseColor("#FF9CB4"))
//            colors.add(Color.MAGENTA)

            pieDataSet.setColors(colors)
            val pieData = PieData(pieDataSet)
            anyChartView.setData(pieData)
            anyChartView.invalidate()

        })



//        pie.title("Overview")
//
//        pie.labels().position("outside")
//
//        pie.legend().title().enabled(true)
//        pie.legend().title()
//            .text("Covid 19 stats")
//            .padding(0.0, 0.0, 10.0, 0.0)
//
//        pie.legend()
//            .position("center-bottom")
//            .itemsLayout(LegendLayout.HORIZONTAL)
//            .align(Align.CENTER)
//
//        anyChartView.setChart(pie)
    }


}
