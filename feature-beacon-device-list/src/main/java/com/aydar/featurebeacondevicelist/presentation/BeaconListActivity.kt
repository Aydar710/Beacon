package com.aydar.featurebeacondevicelist.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.aydar.common.BaseActivity
import com.aydar.featurebeacondevicelist.R
import kotlinx.android.synthetic.main.activity_beacon_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class BeaconListActivity : BaseActivity() {

    private val viewModel: BeaconListViewModel by viewModel()
    private lateinit var adapter: BeaconListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beacon_list)

        initToolbar()

        setupRecycler()
        setupViewModelObservers()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

    override fun onBluetoothAndGpsEnabled() {
        startMonitoring()
    }

    private fun startMonitoring() {
        viewModel.bindService()
        viewModel.startMonitoring()
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
        adapter =
            BeaconListAdapter {
                viewModel.onBeaconClicked(this, it)
            }
        rv_beacons.adapter = adapter
    }

    private fun initToolbar() {
        val toolbar = toolbar as Toolbar
        toolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}