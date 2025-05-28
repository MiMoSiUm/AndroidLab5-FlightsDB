package com.example.androidlab5_flightsdb.data

import kotlinx.coroutines.flow.Flow

class OfflineFavoriteRepository(
    private val favoriteDao: FavoriteDao
): FavoriteRepository {
    override suspend fun insertFavorite(favorite: Favorite) = favoriteDao.insert(favorite)
    override suspend fun deleteFavorite(favorite: Favorite) = favoriteDao.delete(favorite)
    override fun getAllFavorites(): Flow<List<Favorite>> = favoriteDao.getAllFavorites()
}