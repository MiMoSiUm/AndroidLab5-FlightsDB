package com.example.androidlab5_flightsdb.ui

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlab5_flightsdb.data.Airport
import com.example.androidlab5_flightsdb.data.AirportRepository
import com.example.androidlab5_flightsdb.data.Favorite
import com.example.androidlab5_flightsdb.data.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoriteRepository: FavoriteRepository
): ViewModel() {
    var favoritesUiState: StateFlow<FavoritesUiState> =
        favoriteRepository.getAllFavorites().map { FavoritesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavoritesUiState()
            )

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoriteRepository.deleteFavorite(favorite)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class FavoritesUiState(
    val favoritesList: List<Favorite> = listOf()
)