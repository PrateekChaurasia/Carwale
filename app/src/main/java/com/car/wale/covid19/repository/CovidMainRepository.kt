package com.car.wale.covid19.repository

import android.util.Log
import com.car.wale.covid19.Utils.State
import com.car.wale.covid19.api.CovidApiService
import com.car.wale.covid19.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


class CovidMainRepository(private val apiService: CovidApiService) {

    @ExperimentalCoroutinesApi
    fun getData(): Flow<State<ApiResponse>> {
        return object : NetworkBoundRepository<ApiResponse>() {
            override suspend fun fetchFromRemote(): Response<ApiResponse> = apiService.getData()
        }.asFlow().flowOn(Dispatchers.IO)
    }
}