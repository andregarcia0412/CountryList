package com.example.countrylist.ui.screens.countrylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylist.data.remote.RestCountriesService
import com.example.countrylist.data.remote.RetrofitService
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
            val countryList = repository.getCountryList("name,region,flags")
            Log.i("Country API: ", countryList.toString())
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                countryList = countryList
            )
        }
    }
}