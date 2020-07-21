package com.aydar.featurebeacondevicelist.di

import com.aydar.featurebeacondevicelist.BeaconListViewModel
import com.aydar.featurebeacondevicelist.BeaconService
import org.koin.dsl.module

val beaconDeviceListModule = module {

    single { BeaconService(get()) }
    factory { BeaconListViewModel(get()) }
}