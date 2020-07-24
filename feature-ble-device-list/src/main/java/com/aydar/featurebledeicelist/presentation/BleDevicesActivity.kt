package com.aydar.featurebledeicelist.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

        initToolbar()
        setupRecycler()
        setupViewModelObservers()
        viewModel.startScan()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mnu_ble_devices, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_scanning -> {
                viewModel.startScan()
            }
            android.R.id.home -> {
                onBackPressed()
                finish()
            }
        }
        return true
    }

    private fun setupRecycler() {
        adapter = BleDevicesAdapter()
        rv_ble_devices.adapter = adapter
    }

    private fun setupViewModelObservers() {
        viewModel.bleDevices.observe(this, Observer {
            handleUpdatedBleDevices(it)
        })

        viewModel.areDevicesEmpty.observe(this, Observer {
            handleAreDevicesEmpty(it)
        })

        viewModel.isScanning.observe(this, Observer {
            handleIsScanning(it)
        })
    }

    private fun handleIsScanning(isScanning: Boolean) {
        if (isScanning) {
            supportActionBar?.title = getString(R.string.scanning)
        } else {
            supportActionBar?.title = getString(R.string.found_devices)
        }
    }

    private fun handleAreDevicesEmpty(areEmpty: Boolean) {
        if (areEmpty) {
            showNoDevicesText()
        } else {
            hideNoDevicesText()
        }
    }

    private fun handleUpdatedBleDevices(bleDevices: List<BleDevice>) {
        adapter.submitList(bleDevices)
    }

    private fun showNoDevicesText() {
        tv_no_devices.visibility = View.VISIBLE
    }

    private fun hideNoDevicesText() {
        tv_no_devices.visibility = View.GONE
    }

    private fun initToolbar() {
        val toolbar = toolbar as Toolbar
        toolbar.title = getString(R.string.scanning)
        toolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}