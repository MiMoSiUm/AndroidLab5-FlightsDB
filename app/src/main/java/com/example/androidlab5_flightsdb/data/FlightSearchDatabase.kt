package com.example.androidlab5_flightsdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, Favorite::class] , version = 1, exportSchema = false)
abstract class FlightSearchDatabase: RoomDatabase() {
    abstract fun airportDao(): AirportDao
    abstract fun favoriteDao(): FavoriteDao
    companion object {
        @Volatile
        private var Instance: FlightSearchDatabase? = null
        fun getDatabase(context: Context): FlightSearchDatabase { return Instance ?: synchronized(this) {
            Room.databaseBuilder(
                context = context,
                klass = FlightSearchDatabase::class.java,
                name = "flight_search.db"
            )
                .createFromAsset("database/flight_search.db")
                .build()
                .also { Instance = it }
        } }
    }
}