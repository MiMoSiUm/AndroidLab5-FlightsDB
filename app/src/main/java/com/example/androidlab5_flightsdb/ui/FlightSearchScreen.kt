package com.example.androidlab5_flightsdb.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidlab5_flightsdb.data.Airport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val flightSearchUiState = viewModel.flightSearchUiState.collectAsState().value
//    var query = viewModel.currentAirportName

//    Column(modifier = modifier) {
//        TextField(
//            value = query,
//            onValueChange = { viewModel.changeAirportName(it) }
//        )
//        LazyColumn(modifier = modifier) {
//            items(items = flightSearchUiState.airportList, key = { it.id }) { airport ->
//                Text(
//                    text = airport.name
//                )
//            }
//        }
//    }

    Column {
//        FlightSearchBar()
        LazyColumn(modifier = modifier) {
            items(items = flightSearchUiState.airportList, key = { it.id }) { airport ->
                Text(
                    text = airport.name
                )
            }
        }
    }
}