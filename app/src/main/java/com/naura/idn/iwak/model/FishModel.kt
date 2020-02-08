package com.naura.idn.iwak.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FishModel(
    var name: String,
    var address: String,
    var image: Int
): Parcelable