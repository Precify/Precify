package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rohit2810.coview.R
import com.rohit2810.coview.Stats.Network.StatsViewModel
import kotlinx.android.synthetic.main.fragment_stats.*
import com.anychart.enums.LegendLayout
import android.R.attr.data
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import android.widget.Toast
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.AnyChart
import com.anychart.charts.Pie
import com.anychart.AnyChartView

import com.anychart.chart.common.listener.Event
import com.anychart.enums.Align



class StatsFragment : Fragment() {

    private lateinit var statsViewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.rohit2810.coview.R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statsViewModel = ViewModelProvider(this).get(StatsViewModel::class.java)
        val anyChartView : AnyChartView = view.findViewById(com.rohit2810.coview.R.id.any_chart_view)
        anyChartView.setProgressBar(view.findViewById(com.rohit2810.coview.R.id.progress_bar))
        val pie = AnyChart.pie()


        statsViewModel.stats.observe(this, Observer {
            stats_total_active_cases.text = it?.results!!.get(0).totalActiveCases.toString()
            stats_total_cases.text = it?.results!!.get(0).totalCases.toString()
            stats_total_recovered_cases.text = it?.results!!.get(0).totalRecovered.toString()
            stats_total_death_cases.text = it?.results!!.get(0).totalDeaths.toString()

            val data = arrayListOf<ValueDataEntry>()
            data.add(ValueDataEntry("Total active cases", it?.results!!.get(0).totalActiveCases))
            data.add(ValueDataEntry("Total recovered cases", it?.results!!.get(0).totalRecovered))
            data.add(ValueDataEntry("Total death cases", it?.results!!.get(0).totalDeaths))

            pie.data(data as List<DataEntry>?)

        })



        pie.title("Overview")

        pie.labels().position("outside")

        pie.legend().title().enabled(true)
        pie.legend().title()
            .text("Covid 19 stats")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        anyChartView.setChart(pie)
    }


}
