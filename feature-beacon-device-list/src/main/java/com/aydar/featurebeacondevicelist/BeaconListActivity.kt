package com.aydar.featurebeacondevicelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_beacon_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class BeaconListActivity : AppCompatActivity() {

    private val viewModel: BeaconListViewModel by viewModel()
    private val adapter = BeaconListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beacon_list)

        runWithPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION) {
            viewModel.bindService()
            viewModel.startMonitoring()
        }

        setupRecycler()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.beaconsLiveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecycler(){
        rv_beacons.adapter = adapter
    }

    companion object {
        const val TAG = "TAG_Beacon"
    }
}