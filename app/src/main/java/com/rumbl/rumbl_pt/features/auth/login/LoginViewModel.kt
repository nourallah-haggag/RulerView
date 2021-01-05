package com.rumbl.rumbl_pt.features.auth.login

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.network.error.AuthErrorHandling
import com.rumbl.rumbl_pt.bases.network.response.AuthResponse
import com.rumbl.rumbl_pt.bases.repo.AuthRepo
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel

class LoginViewModel(private val repo: AuthRepo) : BaseViewModel() {
    private val loginSingleLiveEvent: SingleLiveEvent<IResult<AuthResponse>> by lazy {
        SingleLiveEvent<IResult<AuthResponse>>()
    }

    fun observeLoginSingleLiveEvent(): SingleLiveEvent<IResult<AuthResponse>> =
        loginSingleLiveEvent

    fun sendPhone(phone: String) {
        repo.loginPt(phone).execute(
            loginSingleLiveEvent, AuthErrorHandling(
                loginSingleLiveEvent,
                ioExceptionMessage,
                socketTimeoutExceptionMessage
            )
        )
    }

}