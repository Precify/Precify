package com.rohit2810.coview.Fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rohit2810.coview.IndiaStats.ViewModel.IndiaStatsViewModel
import com.rohit2810.coview.IndiaStats.Model.Regional
import com.rohit2810.coview.IndiaStats.Adapter.StateAdapter

import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.fragment_state_wise_stats.*


class StateWiseStatsFragment : Fragment() {

    private lateinit var statsViewModel: IndiaStatsViewModel
    private lateinit var stateAdapter: StateAdapter
    private lateinit var bottomSheetDialog: com.rohit2810.coview.Fragments.BottomSheetDialog
    private lateinit var staterv : RecyclerView

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
        stateAdapter =
            StateAdapter(
                view.context,
                { article -> regionalArticleClicked(article) })
        staterv.adapter = stateAdapter
        staterv.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        activity?.let {
            statsViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(activity!!.application)
                .create(IndiaStatsViewModel::class.java)

            statsViewModel.getRegions().observe(this, Observer {
                state_wise_progressBar.visibility = View.GONE
                staterv.visibility = View.VISIBLE
                stateAdapter.setArray(it)
            })
        }
    }

    private fun regionalArticleClicked(regional: Regional) {
        bottomSheetDialog = BottomSheetDialog(regional).getInstance(regional)
        bottomSheetDialog.show(childFragmentManager, "Bottom Sheet")

    }

    private fun createBottomSheetDialog() {
        val view = LayoutInflater.from(activity!!.applicationContext).inflate(R.layout.bottom_sheet_dialog, null)


//            bottomSheetDialog = BottomSheetDialog(activity!!.applicationContext)
//            bottomSheetDialog.setContentView(view)
//            bottomSheetDialog = BottomSheetDialogFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity!!.menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}
