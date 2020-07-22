package com.aydar.featurebeacondetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydar.core.IBeaconService
import com.aydar.core.model.LocalBeacon

class BeaconDetailsViewModel(private val beaconService: IBeaconService) : ViewModel() {

    var beacon: LocalBeacon? = null

    private val _beaconLiveData = MutableLiveData<LocalBeacon>()
    val beaconLiveData = _beaconLiveData

    private val _beaconOutOfZone = MutableLiveData<Boolean>()
    val beaconOutOfZone: LiveData<Boolean> = _beaconOutOfZone

    init {
        beaconService.beaconsLiveData.observeForever(::handleBeaconsUpdate)
    }

    override fun onCleared() {
        super.onCleared()
        beaconService.beaconsLiveData.removeObserver(::handleBeaconsUpdate)
    }

    private fun handleBeaconsUpdate(beacons: List<LocalBeacon>) {
        if (beacons.isNotEmpty()) {
            _beaconLiveData.value = beacons.find { it.uuid == beacon?.uuid }
            _beaconOutOfZone.value?.let {
                if (it) {
                    _beaconOutOfZone.value = false
                }
            }
        } else {
            _beaconOutOfZone.value = true
        }
    }
}