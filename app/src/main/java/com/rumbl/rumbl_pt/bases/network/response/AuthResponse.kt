package com.rumbl.rumbl_pt.bases.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthResponse(
    val access_token: String?,
    val trainer: Trainer?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Trainer(
    val id: Int,
    val name: String,
    val phone: Int,
    val email: String,
    val gender: Int,
    val personal_photo: String?,
    val level: Level?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Level(
    val id: Int,
    val name: String,
    val color: String,
    val sort: Int
) : Parcelable

