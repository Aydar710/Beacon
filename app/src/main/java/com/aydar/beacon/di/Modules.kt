package com.aydar.beacon.di

import com.aydar.beacon.router.AppRouter
import com.aydar.beacon.router.AppRouterImpl
import com.aydar.featurebeacondevicelist.BeaconListRouter
import org.koin.dsl.module

val routerModule = module {
    single<AppRouter> { AppRouterImpl() }
    single<BeaconListRouter> { AppRouterImpl() }
}