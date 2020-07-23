package com.aydar.featurebledeicelist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aydar.core.model.BleDevice
import com.aydar.featurebledeicelist.R
import kotlinx.android.synthetic.main.activity_ble_devices.*
import org.koin.android.viewmodel.ext.android.viewModel

class BleDevicesActivity : AppCompatActivity() {

    private val viewModel: BleDevicesViewModel by viewModel()
    private lateinit var adapter: BleDevicesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ble_devices)

        setupRecycler()
        setupViewModelObservers()
        viewModel.startScan()
    }

    private fun setupRecycler() {
        adapter = BleDevicesAdapter()
        rv_ble_devices.adapter = adapter
    }

    private fun setupViewModelObservers() {
        viewModel.bleDevices.observe(this, Observer {
            handleUpdatedBleDevices(it)
        })
    }

    private fun handleUpdatedBleDevices(bleDevices: List<BleDevice>) {
        adapter.submitList(bleDevices)
    }
}