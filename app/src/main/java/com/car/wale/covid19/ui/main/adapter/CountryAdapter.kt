package com.car.wale.covid19.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.car.wale.covid19.databinding.ItemCountryBinding

import com.car.wale.covid19.ui.main.model.Country
import java.text.SimpleDateFormat


class CountryAdapter : ListAdapter<Country, CountryAdapter.CountryViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =CountryViewHolder(
    ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
      holder.bind(getItem(position))

    class CountryViewHolder(private val binding: ItemCountryBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(details: Country) {
            binding.textState.text = details.country
           /* binding.textLastUpdatedView.text = itemView.context.getString(
                R.string.text_last_updated,
                getPeriod(
                    SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        .parse(details.date)
                )
            )*/

            binding.textConfirmed.text = details.totalConfirmed.toString()
            binding.textActive.text = details.newConfirmed.toString()
            binding.textRecovered.text = details.totalRecovered.toString()
            binding.textDeath.text = details.totalDeaths.toString()

            // New Confirmed
            details.totalConfirmed.let {
                if (it?.equals("0") ?: ("0" == null)) {
                    binding.groupStateNewConfirm.visibility = View.GONE
                } else {
                    binding.groupStateNewConfirm.visibility = View.VISIBLE
                    binding.textStateNewConfirm.text = details.totalConfirmed.toString()
                }
            }

            // New Recovered
            details.totalRecovered.let {
                if (it?.equals("0") ?: ("0" == null)) {
                    binding.groupStateNewRecover.visibility = View.GONE
                } else {
                    binding.groupStateNewRecover.visibility = View.VISIBLE
                    binding.textStateNewRecover.text = details.totalRecovered.toString()
                }
            }

            // New Deaths
            details.totalDeaths.let {
                if (it?.equals("0") ?: ("0" == null)) {
                    binding.groupStateNewDeaths.visibility = View.GONE
                } else {
                    binding.groupStateNewDeaths.visibility = View.VISIBLE
                    binding.textStateNewDeath.text = details.totalDeaths.toString()
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem.country == newItem.country

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem == newItem

        }
    }


}