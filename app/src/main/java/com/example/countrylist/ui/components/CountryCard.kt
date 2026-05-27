package com.example.countrylist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.countrylist.domain.model.CountryListItem

@Composable
fun CountryCard(countryItem: CountryListItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = countryItem.flags.png,
                contentDescription = countryItem.flags.alt
            )
        }
    }
}