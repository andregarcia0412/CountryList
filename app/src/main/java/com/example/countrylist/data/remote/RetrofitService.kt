package com.example.countrylist.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun getRestCountriesService(): RestCountriesService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RestCountriesService::class.java)
    }
}