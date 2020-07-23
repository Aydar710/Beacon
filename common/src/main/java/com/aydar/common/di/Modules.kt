package com.aydar.common.di

import android.bluetooth.BluetoothManager
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val bluetoothModule = module {

    factory {
        val bluetoothManager =
            androidContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }
}