package com.car.wale.covid19.model

import android.provider.Settings
import com.car.wale.covid19.ui.main.model.Country
import com.car.wale.covid19.ui.main.model.Global
import com.squareup.moshi.Json

data class ApiResponse(

    /*  @Json(name = "Global")
      val global: Global,
      @Json(name = "Countries")
      val countries: List<Country>,
      @Json(name = "Date")
      val date: String


  */
    @Json(name = "Global")
    var global: Global,
    @Json(name = "Countries")
    var countries: List<Country>,
    @Json(name = "Date")
    var date: String
)