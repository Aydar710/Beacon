package com.aydar.core.mapper

import android.bluetooth.le.ScanResult
import com.aydar.core.Mapper
import com.aydar.core.model.BleDevice

class BleDeviceMapper : Mapper<ScanResult, BleDevice> {

    override fun map(input: ScanResult): BleDevice =
        BleDevice(input.device.address, input.rssi)
}