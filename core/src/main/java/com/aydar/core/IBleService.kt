package com.aydar.core

import android.bluetooth.le.ScanCallback
import androidx.lifecycle.LiveData
import com.aydar.core.model.BleDevice

interface IBleService {
    var scanCallback: ScanCallback?
    val bleDevices: LiveData<List<BleDevice>>
    fun startScan()
}