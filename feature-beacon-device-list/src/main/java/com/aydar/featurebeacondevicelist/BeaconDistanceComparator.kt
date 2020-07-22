package com.aydar.featurebeacondevicelist

import com.aydar.core.model.LocalBeacon

class BeaconDistanceComparator : Comparator<LocalBeacon> {

    override fun compare(o1: LocalBeacon, o2: LocalBeacon): Int = o1.distance.compareTo(o2.distance)
}