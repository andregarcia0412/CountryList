package com.example.countrylist.ui.screens.countrydetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylist.data.repository.RestCountriesRepository
import com.example.countrylist.domain.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryDetailViewModel(
    private val repository: CountryRepository = RestCountriesRepository()
): ViewModel() {
    private var _uiState = MutableStateFlow(CountryDetailState())
    var uiState = _uiState.asStateFlow()

    fun getCountryDetail(name: String) {
        viewModelScope.launch {
            val countryDetail = repository.getCountryDetail(name)
            Log.i("Country API: ", countryDetail.toString())
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                countryDetail = countryDetail
            )
        }
    }
}