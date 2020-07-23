package com.aydar.featurebledeicelist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.aydar.core.model.BleDevice

class BleDeviceDiffCallback : DiffUtil.ItemCallback<BleDevice>() {

    override fun areItemsTheSame(oldItem: BleDevice, newItem: BleDevice): Boolean =
        oldItem.mac == newItem.mac

    override fun areContentsTheSame(oldItem: BleDevice, newItem: BleDevice): Boolean =
        oldItem == newItem
}