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
    suspend fun insert(favourite: Favorite)
    @Delete
    suspend fun delete(favourite: Favorite)
    @Query("SELECT * from favorite")
    fun getAllFavorites(): Flow<List<Favorite>>
}