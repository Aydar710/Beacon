package com.aydar.beacon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aydar.featurebeacondevicelist.BeaconListActivity
import org.altbeacon.beacon.BeaconConsumer
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.Region

class MainActivity : AppCompatActivity(), BeaconConsumer {

    private lateinit var beaconManager: BeaconManager
    private lateinit var beaconRegion: Region
    private var entryMessageRaised = false
    private var exitMessageRaised = false
    private var rangingMessageRaised = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*runWithPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION) {
            beaconManager = BeaconManager.getInstanceForApplication(this)
            beaconManager.bind(this)
            startBeaconMonitoring()
        }*/
        startActivity(Intent(this, BeaconListActivity::class.java))
    }

    companion object {
        const val TAG = "Beacon"
    }

    override fun onBeaconServiceConnect() {
        Log.d(TAG, "onBeaconServiceConnect")

        beaconManager.addRangeNotifier { beacons, region ->
            if (beacons != null && beacons.isNotEmpty()) {
                beacons.forEach {
                    val logMsg = "Ranging Region. ${region?.uniqueId}. " +
                            "UUID/major/minor: ${region?.id1} / ${region?.id2} / ${region?.id3}"
                    Log.d(TAG + "a", logMsg + "${it.distance}")
                }
                rangingMessageRaised = true
            }
        }

        startBeaconMonitoring()
    }

    private fun startBeaconMonitoring() {
        beaconRegion = Region("Some ranging unique id",
                null,
                null,
                null)

        beaconManager.startMonitoringBeaconsInRegion(beaconRegion)
        beaconManager.startRangingBeaconsInRegion(beaconRegion)
    }

    private fun stopBeaconMonitoring() {
        beaconManager.stopMonitoringBeaconsInRegion(beaconRegion)
        beaconManager.stopRangingBeaconsInRegion(beaconRegion)
    }
}