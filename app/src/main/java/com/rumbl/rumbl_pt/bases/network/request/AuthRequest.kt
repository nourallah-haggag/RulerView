package com.rumbl.rumbl_pt.bases.network.request

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthRequest(
    val phone: String,
    val password: String? = null
) : Parcelable