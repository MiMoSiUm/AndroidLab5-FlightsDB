package com.example.androidlab5_flightsdb.data

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun deleteFavorite(favorite: Favorite)
    fun getAllFavorites(): Flow<List<Favorite>>
    fun getFavorite(departure_code: String, destination_code: String): Flow<Favorite>
}