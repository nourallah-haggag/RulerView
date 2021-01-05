package com.rumbl.rumbl_pt.bases.repo

import com.rumbl.rumbl_pt.bases.network.request.AuthRequest
import com.rumbl.rumbl_pt.bases.network.services.AuthApi

class AuthRepo(private val api: AuthApi) : IRepo {

    fun loginPt(phone: String, password: String? = null) =
        api.login(loginRequestBody = AuthRequest(phone = phone, password = password)).map { it.data }
}