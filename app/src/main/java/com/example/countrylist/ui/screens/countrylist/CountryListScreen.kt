package com.example.countrylist.ui.screens.countrylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.countrylist.ui.components.CountryCard
import kotlinx.serialization.Serializable

@Composable
fun CountryListScreen(navController: NavController) {
    val countryListViewModel: CountryListViewModel = viewModel()
    val screenState = countryListViewModel.uiState.collectAsState().value

    Scaffold(

    ) { innerPadding ->
        if (!screenState.isLoading) {
            LazyColumn (
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    screenState.countryList
                ) { countryItem ->
                    CountryCard(countryItem)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Serializable
object CountryList