package com.aydar.featurebeacondevicelist

import androidx.recyclerview.widget.DiffUtil
import org.altbeacon.beacon.Beacon

class BeaconDiffCallback : DiffUtil.ItemCallback<Beacon>() {

    override fun areItemsTheSame(oldItem: Beacon, newItem: Beacon): Boolean {
        val f = oldItem.id1 == newItem.id1
        return f
    }

    override fun areContentsTheSame(oldItem: Beacon, newItem: Beacon): Boolean {
        return oldItem.id1 == newItem.id1
                && oldItem.id2 == oldItem.id2
                && oldItem.id3 == oldItem.id3
                && oldItem.distance == newItem.distance
    }
}