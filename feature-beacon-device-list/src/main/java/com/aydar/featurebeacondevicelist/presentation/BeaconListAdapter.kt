package com.aydar.featurebeacondevicelist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aydar.core.model.LocalBeacon
import com.aydar.featurebeacondevicelist.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_beacon.view.*

class BeaconListAdapter(val onBeaconClicked: (LocalBeacon) -> Unit) :
    ListAdapter<LocalBeacon, BeaconListAdapter.BeaconViewHolder>(BeaconDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeaconViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_beacon, parent, false)
        return BeaconViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeaconViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BeaconViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun bind(beacon: LocalBeacon) {
            with(containerView) {
                tv_uuid.text = beacon.uuid
                tv_major_value.text = beacon.major.toString()
                tv_minor_value.text = beacon.minor.toString()
                tv_rssi_value.text = beacon.rssi.toString()
                tv_distance_value.text = "${String.format("%.1f", beacon.distance)} м."

                containerView.setOnClickListener {
                    onBeaconClicked.invoke(beacon)
                }
            }
        }
    }

}