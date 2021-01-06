package com.rumbl.rumbl_pt.repo

import com.rumbl.rumbl_pt.network.request.AuthRequest
import com.rumbl.rumbl_pt.network.response.Trainer
import com.rumbl.rumbl_pt.network.services.AuthApi

class AuthRepo(private val api: AuthApi) : IRepo {

    fun loginPt(phone: String, password: String? = null, code: String? = null) =
        api.login(loginRequestBody = AuthRequest(phone = phone, password = password, code = code))
            .doOnSuccess {
                it.data.apply {
                    getSessionService().apply {
                        access_token?.let { token ->
                            saveSessionToken(token = token)
                        }
                        trainer?.let { trainer ->
                            saveUserSession(
                                id = trainer.id,
                                user = trainer,
                                clazz = Trainer::class.java
                            )
                        }
                    }
                }
            }
            .map { it.data }

    fun setPassword(phone: String, password: String, code: String) =
        api.setPassword(
            loginRequestBody = AuthRequest(
                phone = phone,
                password = password,
                code = code
            )
        ).map { it.data }
}