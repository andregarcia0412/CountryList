package com.example.countrylist.ui.screens.countrylist

import com.example.countrylist.domain.model.CountryListItem

data class CountryListState(
    val isLoading: Boolean = true,
    val countryList: List<CountryListItem> = emptyList()
)
