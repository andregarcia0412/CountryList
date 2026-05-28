package com.example.countrylist.ui.screens.countrydetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.countrylist.ui.components.CenteredLoadingIndicator
import com.example.countrylist.ui.components.TopBarDefault
import kotlinx.serialization.Serializable

@Composable
fun CountryDetailScreen(countryDetail: CountryDetail, navController: NavController) {
    val countryDetailViewModel: CountryDetailViewModel = viewModel()
    val screenState = countryDetailViewModel.uiState.collectAsState().value
    countryDetailViewModel.getCountryDetail(countryDetail.countryName)

    Scaffold(
        topBar = { TopBarDefault(countryDetail.countryName) {
            navController.popBackStack()
        } }
    ) { innerPadding ->
        if(!screenState.isLoading) {
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {

            }
        } else {
            CenteredLoadingIndicator()
        }
    }
}

@Serializable
data class CountryDetail(
    val countryName: String
)