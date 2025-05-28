package com.example.androidlab5_flightsdb.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val SEARCH_QUERY = stringPreferencesKey("search_query")
        const val TAG = "UserPreferenceRepo"
    }

    suspend fun saveQueryPreference(searchQuery: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_QUERY] = searchQuery
        }
    }

    val searchQuery: Flow<String> = dataStore.data
        .catch{
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SEARCH_QUERY] ?: ""
        }
}