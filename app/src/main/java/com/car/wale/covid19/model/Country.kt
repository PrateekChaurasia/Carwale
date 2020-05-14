 package com.car.wale.covid19.ui.main.model

import com.squareup.moshi.Json


data class Country(
    @Json(name = "Country")
    var country: String? = null,

    @Json(name = "CountryCode")
    var countryCode: String? = null,

    @Json(name = "Slug")
    var slug: String? = null,

    @Json(name = "NewConfirmed")
    var newConfirmed: Int? = null,

    @Json(name = "TotalConfirmed")
    var totalConfirmed: Int? = null,

    @Json(name = "NewDeaths")
    var newDeaths: Int? = null,

    @Json(name = "TotalDeaths")
    var totalDeaths: Int? = null,

    @Json(name = "NewRecovered")
    var newRecovered: Int? = null,

    @Json(name = "TotalRecovered")
    var totalRecovered: Int? = null,

    @Json(name = "Date")
    var date: String? = null
 )