package com.aydar.featurebeacondevicelist

import android.os.Bundle
import android.view.View
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

        viewModel.isBeaconListEmpty.observe(this, Observer {
            handleIsBeaconsEmpty(it)
        })
    }

    private fun handleIsBeaconsEmpty(isEmpty: Boolean) {
        if (isEmpty) {
            tv_empty.visibility = View.VISIBLE
        } else {
            tv_empty.visibility = View.GONE
        }
    }

    private fun setupRecycler() {
        rv_beacons.adapter = adapter
    }
}