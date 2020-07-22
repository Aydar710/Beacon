package com.aydar.core

import com.aydar.core.model.LocalBeacon
import org.altbeacon.beacon.Beacon

class LocalBeaconMapper : Mapper<Beacon, LocalBeacon> {

    override fun map(input: Beacon): LocalBeacon =
        LocalBeacon(
            input.id1.toString(),
            input.id2.toInt(),
            input.id3.toInt(),
            input.rssi,
            input.distance
        )
}