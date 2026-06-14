package com.example.countrylist.ui.screens.countrylist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.countrylist.ui.components.CenteredLoadingIndicator
import com.example.countrylist.ui.components.CountryCard
import com.example.countrylist.ui.screens.countrydetail.CountryDetail
import com.example.countrylist.ui.components.TopBarDefault
import com.example.countrylist.ui.components.TryAgainPopup
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(navController: NavController) {
    val countryListViewModel: CountryListViewModel = viewModel()
    val screenState = countryListViewModel.uiState.collectAsState().value

    val dropdownItems = listOf(
        "Africa",
        "Antarctica",
        "Asia",
        "Europe",
        "North America",
        "Oceania",
        "South America",
    )

    Scaffold(
        containerColor = Color(0xFF141317),
        topBar = { TopBarDefault("Countries List") }
    ) { innerPadding ->
        screenState.error?.let {
            TryAgainPopup(
                onDismissRequest = { },
                onConfirmation = {
                    countryListViewModel.dismissError()
                    countryListViewModel.getCountryList()
                },
                dialogText = it,
                dialogTitle = "Error"
            )
        }

        if (!screenState.isLoading && screenState.countryList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    ExposedDropdownMenuBox(
                        expanded = screenState.isDropdownOpen,
                        onExpandedChange = { countryListViewModel.toggleDropdownMenu() },
                        modifier = Modifier.width(200.dp)
                    ) {
                        OutlinedTextField(
                            value = screenState.selectedContinent ?: "All",
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor(),
                            placeholder = {
                                Text("Região")
                            },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = screenState.isDropdownOpen
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = screenState.isDropdownOpen,
                            onDismissRequest = { countryListViewModel.toggleDropdownMenu() },
                            containerColor = Color(0xFF1C1B1F)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "All",
                                        color = Color.White
                                    )
                                },
                                onClick = {
                                    countryListViewModel.updateSelectedContinent(null)
                                    countryListViewModel.toggleDropdownMenu()
                                }
                            )

                            dropdownItems.forEach {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = it,
                                            color = Color.White
                                        )
                                    },
                                    onClick = {
                                        countryListViewModel.updateSelectedContinent(it)
                                        countryListViewModel.toggleDropdownMenu()
                                    }
                                )
                            }
                        }
                    }
                }

                items(
                    screenState.displayCountries
                ) { countryItem ->
                    CountryCard(countryItem) {
                        navController.navigate(CountryDetail(countryItem.names.common))
                    }
                }
            }
        } else {
            CenteredLoadingIndicator()
        }
    }
}

@Serializable
object CountryList