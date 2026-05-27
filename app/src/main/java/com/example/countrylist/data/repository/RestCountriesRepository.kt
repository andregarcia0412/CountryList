package com.example.countrylist.data.repository

import com.example.countrylist.data.remote.RestCountriesService
import com.example.countrylist.data.remote.RetrofitService
import com.example.countrylist.domain.model.CountryDetail
import com.example.countrylist.domain.model.CountryListItem
import com.example.countrylist.domain.repository.CountryRepository

class RestCountriesRepository(
    private val service: RestCountriesService = RetrofitService.getRestCountriesService()
): CountryRepository {
    override suspend fun getCountryList(fields: String): List<CountryListItem> {
        try {
            val response = service.getCountryList(fields)
            val countryList = response.body()

            return countryList ?: emptyList()

        } catch(e: Exception) {
            return emptyList()
        }
    }

    override suspend fun getCountryDetail(name: String): CountryDetail {
        val response = service.getCountryDetail(name)
        val countryDetail = response.body()

        return countryDetail ?: throw Exception("País não encontrado")
    }
}