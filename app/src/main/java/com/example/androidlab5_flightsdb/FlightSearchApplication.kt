package com.example.androidlab5_flightsdb

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidlab5_flightsdb.data.AppContainer
import com.example.androidlab5_flightsdb.data.AppDataContainer
import com.example.androidlab5_flightsdb.data.UserPreferencesRepository

private const val SEARCH_QUERY_PREFERENCE_NAME = "query_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SEARCH_QUERY_PREFERENCE_NAME
)

class FlightSearchApplication: Application() {
    lateinit var container: AppContainer
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}