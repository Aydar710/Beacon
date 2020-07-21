package com.aydar.featurebeacondevicelist

import org.altbeacon.beacon.Beacon

class BeaconDistanceComparator : Comparator<Beacon> {

    override fun compare(o1: Beacon, o2: Beacon): Int = o1.distance.compareTo(o2.distance)
}