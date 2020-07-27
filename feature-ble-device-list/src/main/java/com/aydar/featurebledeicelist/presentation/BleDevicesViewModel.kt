package com.aydar.featurebledeicelist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydar.core.model.BleDevice
import com.aydar.featurebledeicelist.domain.BleService

class BleDevicesViewModel(private val bleService: BleService) : ViewModel() {

    private val _bleDevices = MutableLiveData<List<BleDevice>>()
    val bleDevices: LiveData<List<BleDevice>> = _bleDevices

    private val _areDevicesEmpty = MutableLiveData<Boolean>()
    val areDevicesEmpty: LiveData<Boolean> = _areDevicesEmpty

    val isScanning = bleService.isScanning

    init {
        bleService.bleDevices.observeForever(::handleBleDevicesUpdate)
        bleService.isScanning.observeForever(::handleIsScanning)
    }

    fun startScan() {
        bleService.startScan()
    }

    private fun handleBleDevicesUpdate(bleDevices: List<BleDevice>) {
        _bleDevices.value = bleDevices
    }

    private fun handleIsScanning(isScanning: Boolean) {
        if (!isScanning) {
            _areDevicesEmpty.value = _bleDevices.value?.isEmpty()
        } else {
            _areDevicesEmpty.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        bleService.bleDevices.removeObserver(::handleBleDevicesUpdate)
        bleService.isScanning.removeObserver(::handleIsScanning)
        bleService.stopScan()
    }
}