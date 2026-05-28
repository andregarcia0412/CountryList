package com.example.countrylist.ui.screens.countrylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylist.data.repository.RestCountriesRepository
import com.example.countrylist.domain.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryListViewModel(
    private val repository: CountryRepository = RestCountriesRepository()
): ViewModel() {
    private var _uiState = MutableStateFlow(CountryListState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val countryList = repository.getCountryList("name,continents,flags,capital,population")
            Log.i("Country API: ", countryList.toString())
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                countryList = countryList,
            )
        }
    }

    fun toggleDropdownMenu() {
        _uiState.value = _uiState.value.copy(
            isDropdownOpen = !_uiState.value.isDropdownOpen
        )
    }

    fun updateSelectedContinent(continent: String?) {
        _uiState.value = _uiState.value.copy(
            selectedContinent = continent
        )
    }
}