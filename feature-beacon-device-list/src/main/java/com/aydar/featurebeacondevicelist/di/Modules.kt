package com.aydar.featurebeacondevicelist.di

import com.aydar.core.IBeaconService
import com.aydar.featurebeacondevicelist.BeaconListViewModel
import com.aydar.featurebeacondevicelist.BeaconService
import org.koin.dsl.module

val beaconDeviceListModule = module {

    single <IBeaconService>{ BeaconService(get()) }
    factory { BeaconListViewModel(get()) }
}