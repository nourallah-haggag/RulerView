package com.rumbl.rumbl_pt.models

import android.os.Parcelable
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Notification(
    val id: Int,
    val type: Int,
    val title: String,
    val entity_id: Int,
    val entity_type: String,
    val created_at: String,
    val content: String,
    val session: SessionsResponse
) : Parcelable