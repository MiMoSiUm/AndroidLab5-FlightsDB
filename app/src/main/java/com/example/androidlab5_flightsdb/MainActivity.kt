package com.example.androidlab5_flightsdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.androidlab5_flightsdb.ui.AirportSearchScreen
import com.example.androidlab5_flightsdb.ui.FlightSearchScreen
import com.example.androidlab5_flightsdb.ui.navigation.FlightSearchNavGraph
import com.example.androidlab5_flightsdb.ui.theme.AndroidLab5FlightsDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLab5FlightsDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    AirportSearchScreen(modifier = Modifier.padding(innerPadding))
                    FlightSearchNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = rememberNavController()
                        )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AndroidLab5FlightsDBTheme {
//        Greeting("Android")
//    }
//}