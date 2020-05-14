package com.car.wale.covid19.api

import com.car.wale.covid19.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface CovidApiService {

    @GET("/summary")
    suspend fun getData(): Response<ApiResponse>

    companion object {
        const val BASE_URL = "https://api.covid19api.com/"
    }
}