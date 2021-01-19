package com.rumbl.rumbl_pt.features.auth.password_entry

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.error.LoginWithDeviceRegistrationErrorHandling
import com.rumbl.rumbl_pt.network.response.AuthResponse
import com.rumbl.rumbl_pt.repo.AuthRepo

class PasswordEntryViewModel(private val repo: AuthRepo) : BaseViewModel() {
    private val loginSingleLiveEvent: SingleLiveEvent<IResult<Pair<AuthResponse, Any>>> by lazy {
        SingleLiveEvent()
    }

    fun observeLoginSingleLiveEvent(): SingleLiveEvent<IResult<Pair<AuthResponse, Any>>> =
        loginSingleLiveEvent

    fun loginTrainer(phone: String, password: String, fcmToken: String) {
        repo.registerDeviceAndLogin(phone = phone, password = password, fcmToken = fcmToken)
            .execute(
                loginSingleLiveEvent, LoginWithDeviceRegistrationErrorHandling(
                    loginSingleLiveEvent,
                    ioExceptionMessage,
                    socketTimeoutExceptionMessage
                )
            )
    }
}