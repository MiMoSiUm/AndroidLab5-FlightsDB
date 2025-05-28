package com.example.androidlab5_flightsdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidlab5_flightsdb.ui.AirportSearchScreen
import com.example.androidlab5_flightsdb.ui.AirportSearchViewModel
import com.example.androidlab5_flightsdb.ui.AppViewModelProvider
import com.example.androidlab5_flightsdb.ui.FlightSearchScreen
import com.example.androidlab5_flightsdb.ui.FlightSearchViewModel
import kotlinx.serialization.Serializable

@Serializable
object AirportSearchScreenDestination

@Serializable
data class FlightSearchScreenDestination(
    val iata_code: String
)

@Composable
fun FlightSearchNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AirportSearchScreenDestination
    ) {
        composable<AirportSearchScreenDestination> {
            AirportSearchScreen(
                onAirportClicked = { selectedAirport ->
                    val iata_code = selectedAirport.iata_code
                    navController.navigate(FlightSearchScreenDestination(iata_code))
                },
                modifier = modifier
            )
        }
        composable<FlightSearchScreenDestination> { backStackEntry ->
            FlightSearchScreen(modifier = modifier)
        }
    }
}

