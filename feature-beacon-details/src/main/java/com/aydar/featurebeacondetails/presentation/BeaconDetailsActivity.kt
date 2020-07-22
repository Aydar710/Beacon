package com.aydar.featurebeacondetails.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aydar.common.EXTRA_BEACON
import com.aydar.core.model.LocalBeacon
import com.aydar.featurebeacondetails.R
import org.koin.android.viewmodel.ext.android.viewModel

class BeaconDetailsActivity : AppCompatActivity() {

    private val beaconDetailsViewHolder = BeaconDetailsViewHolder(this)
    private val viewModel: BeaconDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beacon_details)

        val beacon = intent.getSerializableExtra(EXTRA_BEACON) as LocalBeacon
        beaconDetailsViewHolder.bind(beacon)
        viewModel.beacon = beacon

        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.beaconLiveData.observe(this, Observer {
            it?.let {
                handleBeaconUpdate(it)
            }
        })

        viewModel.beaconOutOfZone.observe(this, Observer {
            handleBeaconOutOfZone(it)
        })
    }

    private fun handleBeaconUpdate(beacon: LocalBeacon) {
        beaconDetailsViewHolder.bindDistance(beacon.distance)
        beaconDetailsViewHolder.bindRSSI(beacon.rssi)
    }

    private fun handleBeaconOutOfZone(isOutOfZone: Boolean) {
        if (isOutOfZone) {
            beaconDetailsViewHolder.showOutOfZoneText()
        } else {
            beaconDetailsViewHolder.hideOutOfZoneText()
        }
    }
}