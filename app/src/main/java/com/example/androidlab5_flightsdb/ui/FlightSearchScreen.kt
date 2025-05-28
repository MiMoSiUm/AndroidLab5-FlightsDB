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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidlab5_flightsdb.data.Airport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val flightSearchUiState = viewModel.flightSearchUiState.collectAsState().value

    LazyColumn(modifier = modifier) {
        items(items = flightSearchUiState.airportList, key = { it.id }) { airport ->
            val favorite = viewModel.airportsToFavorite(
                flightSearchUiState.currentAirport!!,
                airport
            ).collectAsState().value
            FlightSelectionCard(
                selectedAirport = flightSearchUiState.currentAirport,
                currentAirport = airport,
                isChosen = flightSearchUiState.currentAirport.iata_code == favorite.departure_code &&
                        airport.iata_code == favorite.destination_code,
                onSelect = viewModel::insertFavorite,
                onDeselect = { viewModel.deleteFavorite(favorite) }
            )
        }
    }
}

@Composable
fun FlightSelectionCard(
    modifier: Modifier = Modifier,
    selectedAirport: Airport,
    currentAirport: Airport,
    isChosen: Boolean = false,
    onSelect: (Airport) -> Unit,
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
                AirportCardInfoItem(
                    state = "DEPART",
                    airport = selectedAirport
                )
                AirportCardInfoItem(
                    state = "ARRIVE",
                    airport = currentAirport
                )
            }
            AnimatedVisibility(visible = !isChosen) {
                IconButton(
                    onClick = { onSelect(currentAirport) },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = null)
                }
            }
            AnimatedVisibility(visible = isChosen) {
                IconButton(
                    onClick = { onDeselect() },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                }
            }
        }
    }
}

@Composable
fun AirportCardInfoItem(
    state: String,
    airport: Airport,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
        ) {
            Text(text = state, fontWeight = FontWeight.Light)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                Text(text = airport.iata_code, fontWeight = FontWeight.Bold)
                Text(text = airport.name, fontWeight = FontWeight.Light)
            }
        }
    }
}
