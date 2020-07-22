package com.aydar.beacon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aydar.featurebeacondevicelist.BeaconListActivity
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ranging.setOnClickListener {
            onRangingBtnClicked()
        }

        val permissionOptions = QuickPermissionsOptions(
            rationaleMessage = getString(R.string.location_permission_requiered),
            permanentlyDeniedMessage = "permanentlyDenied"
        )

        //TODO: check bluetooth
        runWithPermissions(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            options = permissionOptions
        ) {
            startActivity(Intent(this, BeaconListActivity::class.java))
        }
    }

    private fun onRangingBtnClicked() {
        val permissionOptions = QuickPermissionsOptions(
            rationaleMessage = getString(R.string.location_permission_requiered),
            permanentlyDeniedMessage = "permanentlyDenied"
        )

        runWithPermissions(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            options = permissionOptions
        ) {
            startActivity(Intent(this, BeaconListActivity::class.java))
        }
    }
}