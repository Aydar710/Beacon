package com.aydar.featurebeacondevicelist.di

import com.aydar.core.IBeaconService
import com.aydar.featurebeacondevicelist.presentation.BeaconListViewModel
import com.aydar.featurebeacondevicelist.domain.BeaconService
import org.koin.dsl.module

val beaconDeviceListModule = module {

    single <IBeaconService>{
        BeaconService(
            get()
        )
    }
    factory {
        BeaconListViewModel(
            get(),
            get()
        )
    }
}