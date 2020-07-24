package com.aydar.featurebledeicelist.domain

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aydar.core.IBleService
import com.aydar.core.mapper.BleDeviceMapper
import com.aydar.core.model.BleDevice

class BleService(private val bluetoothAdapter: BluetoothAdapter) : IBleService {

    override var scanCallback: ScanCallback? = null

    private val _bleDevices = MutableLiveData<List<BleDevice>>()
    override val bleDevices: LiveData<List<BleDevice>> = _bleDevices

    private val _isScanning = MutableLiveData<Boolean>()
    override val isScanning: LiveData<Boolean> = _isScanning

    private val bleDevicesSet = hashSetOf<BleDevice>()

    init {
        setupScanCallback()
        _bleDevices.value = emptyList()
    }

    override fun startScan() {
        bleDevicesSet.clear()
        _bleDevices.value = emptyList()
        bluetoothAdapter.bluetoothLeScanner.startScan(scanCallback)
        _isScanning.value = true
        Handler().postDelayed({
            _isScanning.value?.let {
                if (it) {
                    stopScan()
                }
            }
        }, 6000)
    }

    override fun stopScan() {
        bluetoothAdapter.bluetoothLeScanner.stopScan(scanCallback)
        _isScanning.value = false
    }

    private fun setupScanCallback() {
        scanCallback = object : ScanCallback() {

            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                result?.let {
                    bleDevicesSet.add(BleDeviceMapper().map(it))
                }
                _bleDevices.value = bleDevicesSet.toList()
                super.onScanResult(callbackType, result)
            }

            override fun onBatchScanResults(results: MutableList<ScanResult>?) {
                Log.d("LeScan", results.toString())
                super.onBatchScanResults(results)
            }
        }
    }
}

