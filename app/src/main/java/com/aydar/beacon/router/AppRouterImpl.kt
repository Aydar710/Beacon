package com.aydar.beacon.router

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.aydar.common.EXTRA_BEACON
import com.aydar.core.model.LocalBeacon
import com.aydar.featurebeacondetails.presentation.BeaconDetailsActivity

class AppRouterImpl : AppRouter {

    override fun moveToDetailsScreen(activity: AppCompatActivity, beacon: LocalBeacon) {
        val intent = Intent(activity, BeaconDetailsActivity::class.java).apply {
            putExtra(EXTRA_BEACON, beacon)
        }
        activity.startActivity(intent)
    }
}