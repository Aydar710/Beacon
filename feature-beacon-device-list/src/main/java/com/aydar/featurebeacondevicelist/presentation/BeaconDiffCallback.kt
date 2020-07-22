package com.aydar.featurebeacondevicelist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.aydar.core.model.LocalBeacon

class BeaconDiffCallback : DiffUtil.ItemCallback<LocalBeacon>() {

    override fun areItemsTheSame(oldItem: LocalBeacon, newItem: LocalBeacon): Boolean {
        val f = oldItem.uuid == newItem.uuid
        return f
    }

    override fun areContentsTheSame(oldItem: LocalBeacon, newItem: LocalBeacon): Boolean {
        return oldItem.uuid == newItem.uuid
                && oldItem.major == oldItem.major
                && oldItem.minor == oldItem.minor
                && oldItem.distance == newItem.distance
    }
}