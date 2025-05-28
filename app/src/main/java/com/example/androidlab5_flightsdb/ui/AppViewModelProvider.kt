package com.example.androidlab5_flightsdb.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidlab5_flightsdb.FlightSearchApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FlightSearchViewModel(
                savedInstanceHandle = this.createSavedStateHandle(),
                airportRepository = flightSearchApplication().container.airportRepository
            )
        }

        initializer {
            AirportSearchViewModel(flightSearchApplication().container.airportRepository)
        }
    }
}

fun CreationExtras.flightSearchApplication(): FlightSearchApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)