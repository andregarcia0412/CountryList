package com.example.countrylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countrylist.ui.navigation.AppNavigation
import com.example.countrylist.ui.screens.countrylist.CountryList
import com.example.countrylist.ui.screens.countrylist.CountryListScreen
import com.example.countrylist.ui.theme.CountryListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryListTheme {
                AppNavigation()
            }
        }
    }
}