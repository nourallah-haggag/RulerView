package com.rumbl.rumbl_pt.network.request

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class RegisterDeviceRequest(
    val device_type: Int = 1,
    val token: String
) : Parcelable