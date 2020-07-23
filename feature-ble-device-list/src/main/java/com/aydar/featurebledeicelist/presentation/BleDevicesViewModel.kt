package com.aydar.featurebledeicelist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydar.core.model.BleDevice
import com.aydar.featurebledeicelist.domain.BleService

class BleDevicesViewModel(private val bleService: BleService) : ViewModel() {

    private val _bleDevices = MutableLiveData<List<BleDevice>>()
    val bleDevices: LiveData<List<BleDevice>> = _bleDevices

    init {
        bleService.bleDevices.observeForever(::handleBleDevicesUpdate)
    }

    fun startScan() {
        bleService.startScan()
    }

    private fun handleBleDevicesUpdate(bleDevices: List<BleDevice>) {
        _bleDevices.value = bleDevices
    }

    override fun onCleared() {
        super.onCleared()
        bleService.bleDevices.removeObserver(::handleBleDevicesUpdate)
    }
}