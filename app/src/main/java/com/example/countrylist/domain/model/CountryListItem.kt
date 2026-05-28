package com.example.countrylist.domain.model

data class CountryListItem(
    val flags: FlagDetail,
    val name: NameDetail,
    val continents: List<String>,
    val capital: List<String>?,
    val population: Long,
)
