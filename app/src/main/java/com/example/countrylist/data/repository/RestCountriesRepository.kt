package com.example.countrylist.data.repository

import android.util.Log
import com.example.countrylist.data.remote.RestCountriesService
import com.example.countrylist.data.remote.RetrofitService
import com.example.countrylist.domain.model.CountryDetail
import com.example.countrylist.domain.model.CountryListItem
import com.example.countrylist.domain.repository.CountryRepository

class RestCountriesRepository(
    private val service: RestCountriesService = RetrofitService.getRestCountriesService()
): CountryRepository {
    override suspend fun getCountryList(fields: String): List<CountryListItem> {
        try{
            val response = service.getCountryList(fields)
            val countryList = response.body()

            return countryList ?: emptyList()
        } catch(e: Exception) {
            Log.i("Country API Error: ", e.toString())
            throw Exception("Unable to fetch country list")
        }
    }

    override suspend fun getCountryDetail(name: String): CountryDetail {
        val response = service.getCountryDetail(name)
        val countryDetail = response.body()

        return countryDetail?.firstOrNull() ?: throw Exception("Country not found")
    }
}