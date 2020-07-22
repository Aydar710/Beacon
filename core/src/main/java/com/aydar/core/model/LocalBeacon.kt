package com.aydar.core.model

import java.io.Serializable

data class LocalBeacon(
    val uuid: String,
    val major: Int,
    val minor: Int,
    val rssi: Int,
    val distance: Double
) : Serializable