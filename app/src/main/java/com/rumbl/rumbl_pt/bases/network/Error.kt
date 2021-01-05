package com.rumbl.rumbl_pt.bases.network

import com.rumbl.rumbl_pt.bases.network.error.IError
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Mohamed Shalan on 6/22/20.
 */

@JsonClass(generateAdapter = true)
data class Error(override val message: String) : IError

@JsonClass(generateAdapter = true)
data class AuthError(
    override val message: String,
    @Json(name = "error_code") val errorCode: String?
) : IError
