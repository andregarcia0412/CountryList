package com.example.countrylist.data.model

data class CountryDetail(
    val flag: String,
    val name: NameDetail,
    val capital: List<String>,
    val continents: List<String>,
    val population: Long,
    val area: Int,
    val currencies: Map<String, CurrencyDetail>
)
