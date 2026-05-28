package com.example.countrylist.ui.screens.countrylist

import com.example.countrylist.domain.model.CountryListItem

data class CountryListState(
    val isLoading: Boolean = true,
    val countryList: List<CountryListItem> = emptyList(),
    val selectedContinent: String? = null,
    val isDropdownOpen: Boolean = false,
    val error: String? = null
) {
    val displayCountries: List<CountryListItem>
        get() =
            if (selectedContinent == null)
                countryList
            else
                countryList.filter { it.continents.first() == selectedContinent }
}
