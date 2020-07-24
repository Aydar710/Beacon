package com.aydar.common

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    private val bluetoothAdapter: BluetoothAdapter by inject()
    private lateinit var gpsUtils: GpsUtils
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gpsUtils = GpsUtils(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (bluetoothAdapter.isEnabled && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onBluetoothAndGpsEnabled()
        } else {
            requestTurnOnBluetooth()
            gpsUtils.turnGPSOn { }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_TURN_ON_BLUETOOTH) {
            if (resultCode == Activity.RESULT_OK) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    onBluetoothAndGpsEnabled()
                }
            } else {
                showToast(getString(R.string.can_not_continue_without_enabling_bluetooth))
                finish()
            }
        }
        if (requestCode == REQUEST_TURN_GPS_ON) {
            if (resultCode == Activity.RESULT_OK) {
                if (bluetoothAdapter.isEnabled) {
                    onBluetoothAndGpsEnabled()
                }
            } else {
                showToast(getString(R.string.can_not_continue_without_location))
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    abstract fun onBluetoothAndGpsEnabled()

    private fun showToast(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun requestTurnOnBluetooth() {
        bluetoothAdapter.takeIf { !it.isEnabled }?.apply {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_TURN_ON_BLUETOOTH)
        }
    }

    companion object {
        const val REQUEST_TURN_ON_BLUETOOTH = 12
    }
}