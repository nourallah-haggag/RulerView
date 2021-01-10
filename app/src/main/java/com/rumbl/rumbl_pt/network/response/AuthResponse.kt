package com.rumbl.rumbl_pt.network.response

import android.os.Parcelable
import com.rumbl.rumbl_pt.bases.models.IUser
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
    override val id: Long,
    val name: String,
    val phone: Int?,
    val email: String?,
    val gender: Int?,
    val bio: String?,
    val profile_picture: String?,
    val level: Level?,
    val gallery: List<String>?,
    val specifications: List<InfoItem>,
    val certifications: List<InfoItem>
) : Parcelable, IUser

@JsonClass(generateAdapter = true)
@Parcelize
data class Level(
    val id: Int,
    val name: String,
    val color: String,
    val sort: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class InfoItem(
    val id: Int,
    val name: String
) : Parcelable

