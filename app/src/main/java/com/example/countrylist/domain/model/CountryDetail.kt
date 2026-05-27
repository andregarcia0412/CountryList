package com.example.countrylist.domain.model

data class CountryDetail(
    val flags: FlagDetail,
    val name: NameDetail,
    val capital: List<String>,
    val continents: List<String>,
    val population: Long,
    val area: Int,
    val currencies: Map<String, CurrencyDetail>,
    val languages: Map<String, String>
)
