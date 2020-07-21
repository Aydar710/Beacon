package com.aydar.featurebeacondevicelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.altbeacon.beacon.Beacon
import java.util.*

class BeaconListViewModel(private val beaconService: BeaconService) : ViewModel() {

    private val _beaconsLiveData = MutableLiveData<List<Beacon>>()
    val beaconsLiveData = _beaconsLiveData

    init {
        beaconService.beaconsLiveData.observeForever(::handleBeaconsUpdate)
    }

    override fun onCleared() {
        super.onCleared()
        beaconService.beaconsLiveData.removeObserver(::handleBeaconsUpdate)
    }

    fun bindService() {
        beaconService.bind()
    }

    fun startMonitoring() {
        beaconService.startBeaconMonitoring()
    }

    private fun handleBeaconsUpdate(beacons: List<Beacon>) {
        sortBeaconsByDistance(beacons)
        _beaconsLiveData.value = beacons.toList()
    }

    private fun sortBeaconsByDistance(beacons: List<Beacon>) {
        Collections.sort(beacons, BeaconDistanceComparator())
    }
}