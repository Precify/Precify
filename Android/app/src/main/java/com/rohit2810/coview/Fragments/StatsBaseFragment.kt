package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.rohit2810.coview.ApplicationClass
import com.rohit2810.coview.IndiaStats.ViewModel.IndiaStatsViewModel

import com.rohit2810.coview.R
import kotlinx.android.synthetic.main.fragment_stats_base.*
import timber.log.Timber

class StatsBaseFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var statsViewModel: IndiaStatsViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val tabs : TabLayout = view?.findViewById(R.id.tabs)

        stats_link_states.text = "See countrywise stats"


        stats_link_states.setOnClickListener {
            if(ApplicationClass.position == 1) {
                navController.navigate(R.id.action_statsBaseFragment_to_stateWiseStatsFragment)
            }else {
                navController.navigate(R.id.action_statsBaseFragment_to_countryWiseStatsFragment)
            }
        }

            var id: Int = ApplicationClass.position

            if(ApplicationClass.position == 0) {
                stats_link_states.text = "See countrywise stats"
                tabs.selectTab(tabs.getTabAt(0))
                var fragment = StatsFragment()
                childFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            }else {
                stats_link_states.text = "See statewise stats"

                tabs.selectTab(tabs.getTabAt(1))
                var fragment = IndiaStatsFragment()
                childFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.position == 0) {
                    tabs.setScrollPosition(0, 0f, true)
                    ApplicationClass.position = 0
                    stats_link_states.text = "See countrywise stats"
                     var fragment3 = StatsFragment()
                    childFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment3).commit()
                }else if(tab?.position == 1) {
                    tabs.setScrollPosition(1, 0f, true)
                    stats_link_states.text = "See statewise stats"
                    ApplicationClass.position = 1
                    state_icon.visibility = View.VISIBLE
                    stats_link_states.visibility = View.VISIBLE
                     var fragment2 = IndiaStatsFragment()
                    Timber.d("Entered")
                    childFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment2).commit()
                }
            }
        })
    }


}
