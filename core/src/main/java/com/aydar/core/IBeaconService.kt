package com.aydar.core

import androidx.lifecycle.LiveData
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconConsumer

interface IBeaconService : BeaconConsumer {

    val beaconsLiveData : LiveData<List<Beacon>>

    fun bind()
    fun startBeaconMonitoring()
    fun stopBeaconMonitoring()
}