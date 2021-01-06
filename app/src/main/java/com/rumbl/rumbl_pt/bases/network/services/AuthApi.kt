package com.rumbl.rumbl_pt.bases.network.services

import com.rumbl.rumbl_pt.bases.network.request.AuthRequest
import com.rumbl.rumbl_pt.bases.network.response.AuthResponse
import com.rumbl.rumbl_pt.bases.network.response.base.BaseWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("pt/login")
    fun login(@Body loginRequestBody: AuthRequest): Single<BaseWrapper<AuthResponse>>

    @POST("pt/set-password")
    fun setPassword(@Body loginRequestBody: AuthRequest): Single<BaseWrapper<Any>>
}