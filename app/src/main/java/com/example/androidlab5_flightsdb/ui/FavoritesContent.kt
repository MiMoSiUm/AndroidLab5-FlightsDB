package com.example.androidlab5_flightsdb.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidlab5_flightsdb.data.Airport
import com.example.androidlab5_flightsdb.data.Favorite

@Composable
fun FavoritesContent(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val favoritesUiState = viewModel.favoritesUiState.collectAsState().value

    LazyColumn(modifier = modifier) {
        items(items = favoritesUiState.favoritesList, key = { it.id }) { favorite ->
            FavoriteCard(
                favorite = favorite,
                onDeselect = { viewModel.deleteFavorite(favorite) }
            )
        }
    }
}

@Composable
fun FavoriteCard(
    modifier: Modifier = Modifier,
    favorite: Favorite,
    onDeselect: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(5f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                FavoriteCardInfoItem(
                    state = "DEPART",
                    iata_code = favorite.departure_code
                )
                FavoriteCardInfoItem(
                    state = "ARRIVE",
                    iata_code = favorite.destination_code
                )
            }
            IconButton(
                onClick = {
                    onDeselect()
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
            }
        }
    }
}

@Composable
fun FavoriteCardInfoItem(
    state: String,
    iata_code: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = state, fontWeight = FontWeight.Light)
        Text(text = iata_code, fontWeight = FontWeight.Bold)
    }
}

