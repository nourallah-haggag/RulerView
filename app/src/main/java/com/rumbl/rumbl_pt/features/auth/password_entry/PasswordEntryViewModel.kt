package com.rumbl.rumbl_pt.features.auth.password_entry

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.error.AuthErrorHandling
import com.rumbl.rumbl_pt.network.response.AuthResponse
import com.rumbl.rumbl_pt.repo.AuthRepo

class PasswordEntryViewModel(private val repo: AuthRepo) : BaseViewModel() {
    private val loginSingleLiveEvent: SingleLiveEvent<IResult<AuthResponse>> by lazy {
        SingleLiveEvent()
    }

    fun observeLoginSingleLiveEvent(): SingleLiveEvent<IResult<AuthResponse>> =
        loginSingleLiveEvent

    fun loginTrainer(phone: String, password: String) {
        repo.loginPt(phone = phone, password = password).execute(
            loginSingleLiveEvent, AuthErrorHandling(
                loginSingleLiveEvent,
                ioExceptionMessage,
                socketTimeoutExceptionMessage
            )
        )
    }
}