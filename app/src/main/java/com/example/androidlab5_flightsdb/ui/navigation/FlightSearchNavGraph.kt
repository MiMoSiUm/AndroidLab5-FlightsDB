package com.example.androidlab5_flightsdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun FlightSearchNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    airportSearchViewModel: AirportSearchViewModel = viewModel(factory = AppViewModelProvider.Factory),
    flightSearchViewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    NavHost(
        navController = navController,
        startDestination = "airportSearchScreen"
    ) {
        composable(route = "airportSearchScreen") {
            AirportSearchScreen(
                viewModel = airportSearchViewModel,
                onAirportClicked = { selectedAirport ->
                    val iata_code = selectedAirport.iata_code
                    navController.navigate("flightSearchScreen/$iata_code")
                }
            )
        }
        composable(
            route = "flightSearchScreen/iata_code",
            arguments = listOf(navArgument("iata_code") {
                type = NavType.StringType
            })
        ) {
            FlightSearchScreen(
                viewModel = flightSearchViewModel
            )
        }
    }
}