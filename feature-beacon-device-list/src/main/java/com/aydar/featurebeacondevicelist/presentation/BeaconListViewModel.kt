package com.aydar.featurebeacondevicelist.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydar.core.IBeaconService
import com.aydar.core.model.LocalBeacon
import com.aydar.featurebeacondevicelist.BeaconDistanceComparator
import com.aydar.featurebeacondevicelist.BeaconListRouter
import java.util.*

class BeaconListViewModel(
    private val beaconService: IBeaconService,
    private val router: BeaconListRouter
) : ViewModel() {

    private val _beaconsLiveData = MutableLiveData<List<LocalBeacon>>()
    val beaconsLiveData = _beaconsLiveData

    private val _isBeaconListEmpty = MutableLiveData<Boolean>()
    val isBeaconListEmpty: LiveData<Boolean> = _isBeaconListEmpty

    init {
        beaconService.beaconsLiveData.observeForever(::handleBeaconsUpdate)
    }

    fun bindService() {
        beaconService.bind()
    }

    fun startMonitoring() {
        beaconService.startBeaconMonitoring()
    }

    fun onBeaconClicked(activity: AppCompatActivity, beacon: LocalBeacon) {
        router.moveToDetailsScreen(activity, beacon)
    }

    private fun handleBeaconsUpdate(beacons: List<LocalBeacon>) {
        _isBeaconListEmpty.value = beacons.isEmpty()
        sortBeaconsByDistance(beacons)
        _beaconsLiveData.value = beacons.toList()
    }

    private fun sortBeaconsByDistance(beacons: List<LocalBeacon>) {
        Collections.sort(beacons,
            BeaconDistanceComparator()
        )
    }

    override fun onCleared() {
        super.onCleared()
        beaconService.beaconsLiveData.removeObserver(::handleBeaconsUpdate)
        beaconService.stopBeaconMonitoring()
    }
}