package com.aydar.featurebledeicelist.di

import com.aydar.featurebledeicelist.domain.BleService
import com.aydar.featurebledeicelist.presentation.BleDevicesViewModel
import org.koin.dsl.module

val bleDeviceListModule = module {

    single { BleService(get()) }
    factory { BleDevicesViewModel(get()) }
}