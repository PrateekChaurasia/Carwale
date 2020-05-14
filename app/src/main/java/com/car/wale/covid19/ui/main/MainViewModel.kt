package com.car.wale.covid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.car.wale.covid19.Utils.State
import com.car.wale.covid19.model.ApiResponse
import com.car.wale.covid19.repository.CovidMainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val  repository: CovidMainRepository) : ViewModel(){

     private val _covidLiveData = MutableLiveData<State<ApiResponse>>()

     val covidLiveData: LiveData<State<ApiResponse>>
          get() = _covidLiveData

     fun getData() {
          viewModelScope.launch {
               repository.getData().collect { _covidLiveData.value = it }
          }
     }
}