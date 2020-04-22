package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rohit2810.coview.IndiaStats.Adapter.StateAdapter
import com.rohit2810.coview.IndiaStats.Model.Regional
import com.rohit2810.coview.IndiaStats.ViewModel.IndiaStatsViewModel

import com.rohit2810.coview.R
import com.rohit2810.coview.Stats.CountryAdapter
import com.rohit2810.coview.Stats.Model.CountryResponseItem
import com.rohit2810.coview.Stats.ViewModel.StatsViewModel
import kotlinx.android.synthetic.main.fragment_state_wise_stats.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CountryWiseStatsFragment : Fragment() {

    private lateinit var statsViewModel: StatsViewModel
    private lateinit var staterv: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var bottomSheetDialog: CountryBottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state_wise_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        staterv = view.findViewById(com.rohit2810.coview.R.id.state_recyclerview)
        countryAdapter =
            CountryAdapter(
                view.context,
                { article -> regionalArticleClicked(article) })
        staterv.adapter = countryAdapter
        staterv.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        activity?.let {
            statsViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(activity!!.application)
                .create(StatsViewModel::class.java)

            statsViewModel.getCountryStats().observe(this, Observer {
                if(it.size >= 19) {
                    state_wise_progressBar.visibility = View.GONE
                    staterv.visibility = View.VISIBLE
                    countryAdapter.setArray(it)
                }
            })
        }
    }

    private fun regionalArticleClicked(regional: CountryResponseItem) {
        bottomSheetDialog = CountryBottomSheetDialog(regional).getInstance(regional)
        bottomSheetDialog.show(childFragmentManager, "Bottom Sheet")

    }

    private fun createBottomSheetDialog() {
        val view = LayoutInflater.from(activity!!.applicationContext).inflate(R.layout.bottom_sheet_dialog, null)


//            bottomSheetDialog = BottomSheetDialog(activity!!.applicationContext)
//            bottomSheetDialog.setContentView(view)
//            bottomSheetDialog = BottomSheetDialogFragment()
    }


}
