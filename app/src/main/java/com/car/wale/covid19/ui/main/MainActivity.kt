package com.car.wale.covid19.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.MergeAdapter
import com.car.wale.covid19.Utils.State
import com.car.wale.covid19.databinding.ActivityMainBinding
import com.car.wale.covid19.ui.main.adapter.CountryAdapter
import com.car.wale.covid19.ui.main.adapter.GlobalAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val mGlobalAdapter = GlobalAdapter()
    private val mCountryAdapter = CountryAdapter()
    private var date: String?=null

    private  val adapter = MergeAdapter(mGlobalAdapter,mCountryAdapter)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recycler.adapter= adapter

        initData()

    }

    private fun initData() {
        viewModel.covidLiveData.observe(this, Observer {

                state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(applicationContext, state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val listCountry = state.data.countries
                    val listGlobal = state.data.global
                     date = state.data.date
                    mGlobalAdapter.submitList(listOf(listGlobal))
                    mCountryAdapter.submitList(listCountry)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }


        })

        loadData()
    }

    private fun loadData() {
        viewModel.getData()
    }


    fun MainActivity.getDate(): String {
        return date.toString()

    }
}
