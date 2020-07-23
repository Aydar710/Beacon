package com.aydar.featurebledeicelist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aydar.core.model.BleDevice
import com.aydar.featurebledeicelist.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.ble_device_item.view.*

class BleDevicesAdapter :
    ListAdapter<BleDevice, BleDevicesAdapter.BleDeviceViewHolder>(BleDeviceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BleDeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ble_device_item, parent, false)
        return BleDeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BleDeviceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BleDeviceViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(device: BleDevice) {
            with(containerView) {
                tv_mac_value.text = device.mac
                tv_rssi_value.text = device.rssi.toString()
            }
        }
    }
}