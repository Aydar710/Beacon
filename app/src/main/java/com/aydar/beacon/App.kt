package com.aydar.beacon

import android.app.Application
import com.aydar.beacon.di.routerModule
import com.aydar.featurebeacondetails.di.beaconDetailsModule
import com.aydar.featurebeacondevicelist.di.beaconDeviceListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                beaconDeviceListModule,
                routerModule,
                beaconDetailsModule
            )
        }
    }
}