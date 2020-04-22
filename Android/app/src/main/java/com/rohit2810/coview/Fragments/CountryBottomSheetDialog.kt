package com.rohit2810.coview.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rohit2810.coview.IndiaStats.Model.Regional

import com.rohit2810.coview.R
import com.rohit2810.coview.Stats.Model.CountryResponseItem

class CountryBottomSheetDialog(val country : CountryResponseItem) : BottomSheetDialogFragment() {

    private lateinit var title : TextView
    private lateinit var total: TextView
    private lateinit var active: TextView
    private lateinit var recovered: TextView
    private lateinit var deaths: TextView

    fun getInstance(regional: CountryResponseItem) : CountryBottomSheetDialog {
        return CountryBottomSheetDialog(regional)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = view.findViewById(R.id.state_title)
        total = view.findViewById(com.rohit2810.coview.R.id.state_total_tv)
        active = view.findViewById(com.rohit2810.coview.R.id.state_active_tv)
        recovered = view.findViewById(com.rohit2810.coview.R.id.state_recovered_tv)
        deaths = view.findViewById(com.rohit2810.coview.R.id.state_death_tv)

        title.text = country.country
        total.text = country.cases.toString()
        active.text = country.active.toString()
        recovered.text = country.recovered.toString()
        deaths.text = country.deaths.toString()
    }


}
