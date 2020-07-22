package com.aydar.featurebeacondetails.di

import com.aydar.featurebeacondetails.presentation.BeaconDetailsViewModel
import org.koin.dsl.module

val beaconDetailsModule = module {
    factory { BeaconDetailsViewModel(get()) }
}