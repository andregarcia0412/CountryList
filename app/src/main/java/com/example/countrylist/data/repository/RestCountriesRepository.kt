package com.example.countrylist.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.countrylist.data.remote.RestCountriesService
import com.example.countrylist.data.remote.RetrofitService
import com.example.countrylist.domain.model.CountryDetail
import com.example.countrylist.domain.model.CountryListItem
import com.example.countrylist.domain.repository.CountryRepository

class RestCountriesRepository(
    private val service: RestCountriesService = RetrofitService.getRestCountriesService()
) : CountryRepository {
    private val gson = Gson()
    private val countryListItemType = TypeToken.get(CountryListItem::class.java).type
    private val countryDetailType = TypeToken.get(CountryDetail::class.java).type

    override suspend fun getCountryList(fields: String): List<CountryListItem> {
        try {
            val result = mutableListOf<CountryListItem>()
            val limit = 100
            var offset = 0
            while (true) {
                val page = service.getCountryList(fields, limit, offset)
                val items = page.data.values
                    .filter { it.isJsonArray }
                    .flatMap { arr ->
                        arr.asJsonArray.map { gson.fromJson<CountryListItem>(it, countryListItemType) }
                    }
                result.addAll(items)
                if (items.size < limit) break
                offset += limit
            }
            return result
        } catch (e: Exception) {
            Log.i("Country API Error: ", e.toString())
            throw Exception("Unable to fetch country list")
        }
    }

    override suspend fun getCountryDetail(name: String): CountryDetail {
        val detailFields = "names,flag,codes,capitals,continents,population,area,currencies,languages"
        val raw = service.getCountryDetail(name, detailFields).data.values
            .filter { it.isJsonArray }
            .flatMap { arr -> arr.asJsonArray.toList() }
        Log.i("Detail Raw JSON: ", raw.firstOrNull()?.toString() ?: "empty")
        return raw.map { gson.fromJson<CountryDetail>(it, countryDetailType) }
            .firstOrNull()
            ?: throw Exception("Country not found")
    }
}
