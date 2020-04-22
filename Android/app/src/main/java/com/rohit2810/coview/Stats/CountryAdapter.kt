package com.rohit2810.coview.Stats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rohit2810.coview.IndiaStats.Model.Regional
import com.rohit2810.coview.R
import com.rohit2810.coview.Stats.Model.CountryResponseItem

class CountryAdapter internal constructor(
    val context: Context,
    private val clickListener : (CountryResponseItem) -> Unit): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private var countries: List<CountryResponseItem> = emptyList()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView = itemView.findViewById(R.id.state_item_tv)
        var stateTotal: TextView = itemView.findViewById(R.id.state_item_total)
        var stateRecovered : TextView = itemView.findViewById(R.id.state_item_recovered)

        fun bind(regional: CountryResponseItem, clickListener: (CountryResponseItem) -> Unit) {
            titleTextView.text = regional.country
            stateTotal.text = "Total: " + regional.cases.toString()
            stateRecovered.text = "Recovered: " + regional.recovered.toString()
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
        return countries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position], clickListener)
    }

    internal fun setArray(regionals: List<CountryResponseItem>) {
        if (regionals.isNotEmpty()) {
            this.countries = regionals
        }
        notifyDataSetChanged()
    }
}