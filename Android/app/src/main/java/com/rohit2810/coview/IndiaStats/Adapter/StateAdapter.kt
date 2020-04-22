package com.rohit2810.coview.IndiaStats.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rohit2810.coview.IndiaStats.Model.Regional
import com.rohit2810.coview.R

class StateAdapter internal constructor(
    val context: Context,
    private val clickListener : (Regional) -> Unit): RecyclerView.Adapter<StateAdapter.ViewHolder>() {

    private var regionals: List<Regional> = emptyList()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView = itemView.findViewById(R.id.state_item_tv)
        var stateTotal: TextView = itemView.findViewById(R.id.state_item_total)
        var stateRecovered : TextView = itemView.findViewById(R.id.state_item_recovered)

        fun bind(regional: Regional, clickListener: (Regional) -> Unit) {
            titleTextView.text = regional.loc
            stateTotal.text = "Total: " + regional.totalConfirmed.toString()
            stateRecovered.text = "Recovered: " + regional.discharged.toString()
            itemView.setOnClickListener{
                clickListener(regional)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.state_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return regionals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(regionals[position], clickListener)
    }

    internal fun setArray(regionals: List<Regional>) {
        if (regionals.isNotEmpty()) {
            this.regionals = regionals
        }
        notifyDataSetChanged()
    }
}