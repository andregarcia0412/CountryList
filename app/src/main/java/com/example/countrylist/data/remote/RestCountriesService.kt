package com.example.countrylist.data.remote

import com.example.countrylist.domain.model.CountryDetail
import com.example.countrylist.domain.model.CountryListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestCountriesService {
    @GET("all")
    suspend fun getCountryList(
        @Query("fields") fields: String
    ): Response<List<CountryListItem>>

    @GET("name/{name}")
    suspend fun getCountryDetail(@Path("name") name: String): Response<CountryDetail>
}