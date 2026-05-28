package com.example.countrylist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDefault(
    title: String,
    onReturn: (() -> Unit)? = null
    ) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF141317)
            ),

            navigationIcon = {
                if(onReturn != null) {
                    IconButton(
                        onClick = onReturn,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(28.dp),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            },
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFF393939)
        )
    }
}