package com.example.androidlab5_flightsdb

import android.app.Application
import com.example.androidlab5_flightsdb.data.AppContainer
import com.example.androidlab5_flightsdb.data.AppDataContainer

class FlightSearchApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}