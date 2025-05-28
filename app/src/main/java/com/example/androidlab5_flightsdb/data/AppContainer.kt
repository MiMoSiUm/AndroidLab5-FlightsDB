package com.example.androidlab5_flightsdb.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

interface AppContainer {
    val airportRepository: AirportRepository
    val favoriteRepository: FavoriteRepository
    val userPreferencesRepository: UserPreferencesRepository
}


class AppDataContainer(
    private val context: Context
): AppContainer {
    private val SEARCH_QUERY_PREFERENCE_NAME = "query_preferences"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = SEARCH_QUERY_PREFERENCE_NAME
    )
    override val airportRepository: AirportRepository by lazy {
        OfflineAirportRepository(FlightSearchDatabase.getDatabase(context).airportDao())
    }
    override val favoriteRepository: FavoriteRepository by lazy {
        OfflineFavoriteRepository(FlightSearchDatabase.getDatabase(context).favoriteDao())
    }
    override val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(this.context.dataStore)
    }
}