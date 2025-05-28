package com.example.androidlab5_flightsdb.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

class AirportSearchViewModel(private val airportRepository: AirportRepository) : ViewModel() {
    var currentAirportName by mutableStateOf("")

    var airportSearchUiState: StateFlow<AirportSearchUiState> =
        airportRepository.getAllAirportsStream(currentAirportName).map { AirportSearchUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AirportSearchUiState()
            )

    fun changeAirportName(airportName: String) {
        currentAirportName = airportName
        airportSearchUiState =
            airportRepository.getAllAirportsStream(currentAirportName).map { AirportSearchUiState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = AirportSearchUiState()
                )
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class AirportSearchUiState(
    val airportList: List<Airport> = listOf()
)