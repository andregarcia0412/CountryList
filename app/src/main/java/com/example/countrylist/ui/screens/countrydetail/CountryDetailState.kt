package com.example.countrylist.ui.screens.countrydetail

import com.example.countrylist.domain.model.CountryDetail

data class CountryDetailState(
    val countryDetail: CountryDetail? = null,
    val isLoading: Boolean = false
)
