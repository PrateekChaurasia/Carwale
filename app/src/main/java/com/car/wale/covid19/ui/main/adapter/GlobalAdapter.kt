package com.car.wale.covid19.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.car.wale.covid19.R
import com.car.wale.covid19.Utils.getPeriod
import com.car.wale.covid19.databinding.ItemRowBinding
import com.car.wale.covid19.model.ApiResponse
import com.car.wale.covid19.ui.main.MainActivity
import com.car.wale.covid19.ui.main.model.Country
import com.car.wale.covid19.ui.main.model.Global
import java.text.SimpleDateFormat


class GlobalAdapter : ListAdapter<Global,GlobalAdapter.TotalViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= TotalViewHolder (
        ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) =
        holder.bind(getItem(position))


    class TotalViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(details: Global) {
            /*  binding.textLastUpdatedView.text = itemView.context.getString(
                  R.string.text_last_updated,
                  getPeriod(
                      SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse()
                  )
              )*/

            binding.textConfirmed.text = details.totalConfirmed.toString()
            binding.textActive.text = details.newConfirmed.toString()
            binding.textRecovered.text = details.totalRecovered.toString()
            binding.textDeceased.text = details.totalDeaths.toString()

            // New Confirmed
            details.totalConfirmed.let {
                if (it ==0) {
                    binding.groupNewConfirmed.visibility = View.GONE
                } else {
                    binding.groupNewConfirmed.visibility = View.VISIBLE
                    binding.textNewConfirmed.text = details.totalConfirmed.toString()
                }
            }

            // New Recovered
            details.totalRecovered.let {
                if (it==0) {
                    binding.groupNewRecovered.visibility = View.GONE
                } else {
                    binding.groupNewRecovered.visibility = View.VISIBLE
                    binding.textNewRecovered.text = details.totalRecovered.toString()
                }
            }

            // New Deaths
            details.totalDeaths.let {
                if (it==0) {
                    binding.groupNewDeaths.visibility = View.GONE
                } else {
                    binding.groupNewDeaths.visibility = View.VISIBLE
                    binding.textNewDeaths.text = details.totalDeaths.toString()
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Global>() {
            override fun areItemsTheSame(oldItem: Global, newItem: Global): Boolean =
                oldItem.newConfirmed == newItem.newConfirmed

            override fun areContentsTheSame(oldItem: Global, newItem: Global): Boolean =
                oldItem == newItem

        }
    }

}