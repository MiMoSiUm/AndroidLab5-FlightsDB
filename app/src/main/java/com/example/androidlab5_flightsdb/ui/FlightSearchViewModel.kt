package com.example.androidlab5_flightsdb.ui

import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlab5_flightsdb.data.Airport
import com.example.androidlab5_flightsdb.data.AirportRepository
import com.example.androidlab5_flightsdb.data.Favorite
import com.example.androidlab5_flightsdb.data.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.first

class FlightSearchViewModel(
    savedInstanceHandle: SavedStateHandle,
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val selected_airport_iata_code: String = checkNotNull(savedInstanceHandle["iata_code"])

    var flightSearchUiState: StateFlow<FlightSearchUiState> =
        airportRepository.getAllAirportsStream("").map {
            FlightSearchUiState(
                currentAirport = it.first { airport -> airport.iata_code == selected_airport_iata_code },
                airportList = it.filter { airport -> airport.iata_code != selected_airport_iata_code }
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FlightSearchUiState()
            )

    fun airportsToFavorite(
        departureAirport: Airport,
        destinationAirport: Airport
    ): StateFlow<Favorite> = favoriteRepository.getFavorite(
        departure_code = departureAirport.iata_code,
        destination_code = destinationAirport.iata_code
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = Favorite(0, "", "")
    )

    fun insertFavorite(destinationAirport: Airport) {
        viewModelScope.launch {
            favoriteRepository.insertFavorite(Favorite(
                departure_code = selected_airport_iata_code,
                destination_code = destinationAirport.iata_code
            ))
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoriteRepository.deleteFavorite(favorite)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class FlightSearchUiState(
    val currentAirport: Airport? = null,
    val airportList: List<Airport> = listOf()
)