package com.example.countrylist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.countrylist.ui.screens.countrydetail.CountryDetail
import com.example.countrylist.ui.screens.countrydetail.CountryDetailScreen
import com.example.countrylist.ui.screens.countrylist.CountryList
import com.example.countrylist.ui.screens.countrylist.CountryListScreen

@Composable()
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CountryList
    ) {
        composable<CountryList> {
            CountryListScreen(navController)
        }

        composable<CountryDetail> { backStackEntry ->
            val countryDetail = backStackEntry.toRoute<CountryDetail>()
            CountryDetailScreen(countryDetail, navController)
        }


    }
}