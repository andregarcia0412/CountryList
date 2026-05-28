package com.example.countrylist.ui.screens.countrylist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header() {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Countries List",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF141317)
            )
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFF393939)
        )
    }
}