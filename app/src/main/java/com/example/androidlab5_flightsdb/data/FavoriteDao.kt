package com.example.androidlab5_flightsdb.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)
    @Delete
    suspend fun delete(favorite: Favorite)
    @Query("SELECT * from favorite")
    fun getAllFavorites(): Flow<List<Favorite>>
    @Query("SELECT * from favorite WHERE departure_code = :departure_code AND destination_code = :destination_code")
    fun getFavorite(departure_code: String, destination_code: String): Flow<Favorite?>
}