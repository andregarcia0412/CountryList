package com.example.countrylist.domain.repository

import com.example.countrylist.domain.model.CountryDetail
import com.example.countrylist.domain.model.CountryListItem

interface CountryRepository {
    suspend fun getCountryList(fields: String): List<CountryListItem>
    suspend fun getCountryDetail(name: String): CountryDetail
}