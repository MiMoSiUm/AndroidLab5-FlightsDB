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
    private val airportRepository: AirportRepository,
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

    fun favoriteToAirports(favorite: Favorite): List<StateFlow<Airport>> = listOf(
        airportRepository.getAirportStream(iata_code = favorite.departure_code).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = Airport(0, "","", 0)
        ),
        airportRepository.getAirportStream(iata_code = favorite.destination_code).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = Airport(0, "","", 0)
        ),
    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class FavoritesUiState(
    val favoritesList: List<Favorite> = listOf()
) {

}