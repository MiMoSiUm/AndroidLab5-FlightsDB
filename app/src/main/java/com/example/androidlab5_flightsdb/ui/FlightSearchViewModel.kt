package com.example.androidlab5_flightsdb.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlab5_flightsdb.data.Airport
import com.example.androidlab5_flightsdb.data.AirportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FlightSearchViewModel(
    savedInstanceHandle: SavedStateHandle,
    private val airportRepository: AirportRepository
) : ViewModel() {
//    private val selected_airport_iata_code: String = checkNotNull(savedInstanceHandle["iata_code"])
    private val selected_airport_iata_code: String? = savedInstanceHandle["iata_code"]

//    var currentAirportName by mutableStateOf("")

    var flightSearchUiState: StateFlow<FlightSearchUiState> =
        airportRepository.getAllAirportsExceptStream(selected_airport_iata_code ?: "SVO").map { FlightSearchUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FlightSearchUiState()
            )

//    fun changeAirportName(airportName: String) {
//        currentAirportName = airportName
//        flightSearchUiState =
//            airportRepository.getAllAirportsStream(currentAirportName).map { FlightSearchUiState(it) }
//                .stateIn(
//                    scope = viewModelScope,
//                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                    initialValue = FlightSearchUiState()
//                )
//    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class FlightSearchUiState(
    val airportList: List<Airport> = listOf()
)