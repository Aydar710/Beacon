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

    private val bleDevicesSet = hashSetOf<BleDevice>()

    init {
        setupScanCallback()
        _bleDevices.value = emptyList()
    }

    override fun startScan() {
        bluetoothAdapter.bluetoothLeScanner.startScan(scanCallback)
        Handler().postDelayed({
            bluetoothAdapter.bluetoothLeScanner.stopScan(scanCallback)
        }, 6000)
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

            override fun onScanFailed(errorCode: Int) {
                super.onScanFailed(errorCode)
            }

            override fun onBatchScanResults(results: MutableList<ScanResult>?) {
                Log.d("LeScan", results.toString())
                super.onBatchScanResults(results)
            }
        }
    }
}

