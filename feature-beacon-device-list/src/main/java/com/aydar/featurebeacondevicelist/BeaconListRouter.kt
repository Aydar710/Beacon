package com.aydar.featurebeacondevicelist

import androidx.appcompat.app.AppCompatActivity
import com.aydar.core.model.LocalBeacon

interface BeaconListRouter {

    fun moveToDetailsScreen(activity: AppCompatActivity, beacon: LocalBeacon)
}