package com.example.androidlab5_flightsdb.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAllAirportsStream(query: String): Flow<List<Airport>>
    fun getAllAirportsExceptStream(query: String): Flow<List<Airport>>
}