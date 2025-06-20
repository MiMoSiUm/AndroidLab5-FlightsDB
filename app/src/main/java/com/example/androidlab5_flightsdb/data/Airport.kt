package com.example.androidlab5_flightsdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey
    val id: Int,
    val iata_code: String,
    val name: String,
    val passengers: Int
)