package com.example.countrylist.ui.screens.countrydetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.countrylist.ui.components.CenteredLoadingIndicator
import com.example.countrylist.ui.components.InfoCard
import com.example.countrylist.ui.components.TopBarDefault
import com.example.countrylist.ui.components.TryAgainPopup
import com.example.countrylist.util.NumberFormatter
import kotlinx.serialization.Serializable

@Composable
fun CountryDetailScreen(countryDetail: CountryDetail, navController: NavController) {
    val countryDetailViewModel: CountryDetailViewModel = viewModel()
    val screenState = countryDetailViewModel.uiState.collectAsState().value

    countryDetailViewModel.getCountryDetail(countryDetail.countryName)

    Scaffold(
        topBar = { TopBarDefault(countryDetail.countryName) {
            navController.popBackStack()
        } },
        containerColor = Color(0xFF141317)
    ) { innerPadding ->
        screenState.error?.let {
            TryAgainPopup(
                onDismissRequest = { },
                onConfirmation = {
                    countryDetailViewModel.dismissError()
                    countryDetailViewModel.getCountryDetail(countryDetail.countryName)
                },
                dialogText = it,
                dialogTitle = "Error"
            )
        }

        if (!screenState.isLoading && screenState.countryDetail != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                AsyncImage(
                    model = screenState.countryDetail.flagUrl,
                    contentDescription = screenState.countryDetail.flag.alt,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1C1B1F)
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = screenState.countryDetail.names.common,
                            color = Color.White,
                            fontSize = 20.sp,
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = screenState.countryDetail.names.official,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        InfoCard(
                            label = "CONTINENT",
                            value = screenState.countryDetail.continents.firstOrNull() ?: "No continent",
                            icon = Icons.Default.Public,
                            modifier = Modifier.weight(1f)
                        )

                        InfoCard(
                            label = "CAPITAL",
                            value = screenState.countryDetail.capitals.firstOrNull()?.name ?: "No capital",
                            icon = Icons.Default.LocationCity,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoCard(
                            label = "POPULATION",
                            value = NumberFormatter.toCompactString(screenState.countryDetail.population),
                            icon = Icons.Default.People,
                            modifier = Modifier.weight(1f)
                        )

                        InfoCard(
                            label = "AREA",
                            value = NumberFormatter.toLocaleString((screenState.countryDetail.area.km2 ?: 0.0).toLong()) + "km²",
                            icon = Icons.Default.SquareFoot,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    InfoCard(
                        label = "CURRENCY",
                        value = screenState.countryDetail.currencies.joinToString(),
                        icon = Icons.Default.Payments,
                    )

                    InfoCard(
                        label = "LANGUAGE",
                        value = screenState.countryDetail.languages.mapNotNull { it.name }.joinToString(),
                        icon = Icons.Default.Translate,
                    )
                }
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
