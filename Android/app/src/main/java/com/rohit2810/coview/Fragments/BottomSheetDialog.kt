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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BottomSheetDialog internal constructor(
    val regional: Regional
): BottomSheetDialogFragment() {

    private lateinit var title : TextView
    private lateinit var total: TextView
    private lateinit var active: TextView
    private lateinit var recovered: TextView
    private lateinit var deaths: TextView

    fun getInstance(regional: Regional) : BottomSheetDialog {
        return BottomSheetDialog(regional)
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

        title.text = regional.loc
        total.text = regional.totalConfirmed.toString()
        active.text = (regional.totalConfirmed - regional.discharged - regional.deaths).toString()
        recovered.text = regional.discharged.toString()
        deaths.text = regional.deaths.toString()
    }


}
