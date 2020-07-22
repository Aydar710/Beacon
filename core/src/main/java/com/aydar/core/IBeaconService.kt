package com.aydar.core

import androidx.lifecycle.LiveData
import com.aydar.core.model.LocalBeacon
import org.altbeacon.beacon.BeaconConsumer

interface IBeaconService : BeaconConsumer {

    val beaconsLiveData : LiveData<List<LocalBeacon>>

    fun bind()
    fun startBeaconMonitoring()
    fun stopBeaconMonitoring()
}