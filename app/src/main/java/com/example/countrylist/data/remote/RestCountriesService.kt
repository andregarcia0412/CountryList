package com.example.countrylist.data.remote

import com.example.countrylist.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestCountriesService {
    @GET("countries/v5")
    suspend fun getCountryList(
        @Query("response_fields") fields: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse

    @GET("countries/v5/names.common/{name}")
    suspend fun getCountryDetail(
        @Path("name") name: String,
        @Query("response_fields") fields: String
    ): ApiResponse
}
