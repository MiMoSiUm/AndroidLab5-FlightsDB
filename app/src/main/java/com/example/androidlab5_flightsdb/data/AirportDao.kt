package com.example.androidlab5_flightsdb.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * from airport WHERE iata_code LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%' ORDER BY passengers DESC")
    fun getFilteredAirports(query: String): Flow<List<Airport>>
    @Query("SELECT * from airport WHERE iata_code != :iata_code ORDER BY passengers DESC")
    fun getAllAirportsExcept(iata_code: String): Flow<List<Airport>>
    @Query("SELECT * from airport WHERE iata_code = :iata_code")
    fun getAirportByCode(iata_code: String): Flow<Airport>
}