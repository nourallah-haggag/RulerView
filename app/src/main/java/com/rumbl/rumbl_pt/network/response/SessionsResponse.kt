package com.rumbl.rumbl_pt.network.response

import android.os.Parcelable
import com.rumbl.rumbl_pt.models.IHomeScreenModel
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class SessionsResponse(
    val id: Int,
    val customer_latitude: Double?,
    val customer_longitude: Double?,
    val customer_address: String?,
    val date: String,
    val status: Int,
    val time_slot: TimeSlot,
    val session_type: Int,
    val num_of_sessions_with_customer: Int,
    val customer: Customer
) : Parcelable, IHomeScreenModel

@JsonClass(generateAdapter = true)
@Parcelize
data class Customer(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String?,
    val gender: Int,
    val avatar: String?,
    val date_of_birth: String?,
    val height: Int?,
    val weight: Int?,
    val bmi: Double?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class TimeSlot(
    val id: Int,
    val from: String,
    val to: String,
    val status: Int,
    val personal_trainer_id: Int,
    val created_at: String,
    val updated_at: String
) : Parcelable