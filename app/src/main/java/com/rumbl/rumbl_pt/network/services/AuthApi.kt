package com.rumbl.rumbl_pt.network.services

import com.rumbl.rumbl_pt.network.request.AuthRequest
import com.rumbl.rumbl_pt.network.request.RegisterDeviceRequest
import com.rumbl.rumbl_pt.network.response.AuthResponse
import com.rumbl.rumbl_pt.network.response.base.BaseWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("pt/login")
    fun login(@Body loginRequestBody: AuthRequest): Single<BaseWrapper<AuthResponse>>

    @POST("pt/set-password")
    fun setPassword(@Body loginRequestBody: AuthRequest): Single<BaseWrapper<Any>>

    @POST("pt/register-device")
    fun registerDevice(@Body registerDeviceBody: RegisterDeviceRequest) : Single<BaseWrapper<Any>>
}