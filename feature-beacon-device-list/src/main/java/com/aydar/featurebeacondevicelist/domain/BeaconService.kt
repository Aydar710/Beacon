package com.aydar.featurebeacondevicelist.domain

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aydar.core.IBeaconService
import com.aydar.core.LocalBeaconMapper
import com.aydar.core.model.LocalBeacon
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.Region

class BeaconService(private val context: Context) : IBeaconService {

    private val _beaconsLiveData = MutableLiveData<List<LocalBeacon>>()
    override val beaconsLiveData: LiveData<List<LocalBeacon>> = _beaconsLiveData

    private var beaconManager: BeaconManager = BeaconManager.getInstanceForApplication(context)
    private lateinit var beaconRegion: Region

    override fun onBeaconServiceConnect() {
        beaconManager.addRangeNotifier { beacons, region ->
            beacons?.let {
                _beaconsLiveData.value = it.map {
                    LocalBeaconMapper().map(it)
                }
            }
        }
    }

    override fun getApplicationContext(): Context = context

    override fun unbindService(p0: ServiceConnection?) {
        /*Not yet implemented*/
    }

    override fun bindService(p0: Intent?, p1: ServiceConnection?, p2: Int): Boolean {
        /*Not yet implemented*/
        return true
    }

    override fun bind() {
        beaconManager.bind(this)
    }

    override fun startBeaconMonitoring() {
        beaconRegion = Region(
            "Some ranging unique id",
            null,
            null,
            null
        )

        beaconManager.startMonitoringBeaconsInRegion(beaconRegion)
        beaconManager.startRangingBeaconsInRegion(beaconRegion)
    }

    override fun stopBeaconMonitoring() {
        beaconManager.stopMonitoringBeaconsInRegion(beaconRegion)
        beaconManager.stopRangingBeaconsInRegion(beaconRegion)
    }
}