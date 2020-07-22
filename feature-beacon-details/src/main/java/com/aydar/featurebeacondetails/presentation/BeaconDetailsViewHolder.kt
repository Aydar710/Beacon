package com.aydar.featurebeacondetails.presentation

import android.view.View
import com.aydar.core.model.LocalBeacon
import kotlinx.android.synthetic.main.activity_beacon_details.*

class BeaconDetailsViewHolder(private val activity: BeaconDetailsActivity) {

    fun bind(beacon: LocalBeacon) {
        with(activity) {
            tv_uuid.text = beacon.uuid
            tv_major_value.text = beacon.major.toString()
            tv_minor_value.text = beacon.minor.toString()
            tv_rssi_value.text = beacon.rssi.toString()
            tv_distance_value.text = "${String.format("%.1f", beacon.distance)} м."
        }
    }

    fun bindDistance(distance: Double) {
        activity.tv_distance_value.text = "${String.format("%.1f", distance)} м."
    }

    fun bindRSSI(rssi: Int) {
        activity.tv_rssi_value.text = rssi.toString()
    }

    fun showOutOfZoneText() {
        with(activity) {
            tv_distance_value.visibility = View.GONE
            tv_out_of_zone.visibility = View.VISIBLE
        }
    }

    fun hideOutOfZoneText() {
        with(activity) {
            tv_distance_value.visibility = View.VISIBLE
            tv_out_of_zone.visibility = View.GONE
        }
    }
}