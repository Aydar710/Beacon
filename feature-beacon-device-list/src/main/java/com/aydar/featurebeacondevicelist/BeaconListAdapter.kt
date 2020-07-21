package com.aydar.featurebeacondevicelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_beacon.view.*
import org.altbeacon.beacon.Beacon

class BeaconListAdapter :
    ListAdapter<Beacon, BeaconListAdapter.BeaconViewHolder>(BeaconDiffCallback()) {

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


        fun bind(beacon: Beacon) {
            with(containerView) {
                tv_uuid.text = beacon.id1.toString()
                tv_major_value.text = beacon.id2.toString()
                tv_minor_value.text = beacon.id3.toString()
                tv_rssi_value.text = beacon.rssi.toString()
                tv_distance_value.text = "${String.format("%.1f", beacon.distance)} m."
            }
        }
    }

}