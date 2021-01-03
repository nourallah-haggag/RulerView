package com.rumbl.rumbl_pt.bases.network

import com.rumbl.rumbl_pt.bases.network.error.IError
import com.squareup.moshi.JsonClass

/**
 * Created by Mohamed Shalan on 6/22/20.
 */

@JsonClass(generateAdapter = true)
data class Error(override val message: String) : IError
