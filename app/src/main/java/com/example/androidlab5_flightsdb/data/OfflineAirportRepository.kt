package com.example.androidlab5_flightsdb.data

import kotlinx.coroutines.flow.Flow

class OfflineAirportRepository(
    private val airportDao: AirportDao
): AirportRepository {
    override fun getAllAirportsStream(query: String): Flow<List<Airport>> = airportDao.getFilteredAirports(query)
    override fun getAllAirportsExceptStream(query: String): Flow<List<Airport>> = airportDao.getAllAirportsExcept(query)
}