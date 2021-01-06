package com.rumbl.rumbl_pt.features.auth.verification

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.repo.AuthRepo
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel

class VerificationViewModel(private val repo: AuthRepo) : BaseViewModel() {

    private val verifyUserSingleLiveEvent: SingleLiveEvent<IResult<Any>> by lazy {
        SingleLiveEvent()
    }

    fun observeVerifyUserEvent(): SingleLiveEvent<IResult<Any>> = verifyUserSingleLiveEvent

    fun verifyUser(phone: String, code: String, password: String) {
        repo.setPassword(phone = phone, code = code, password = password)
            .execute(verifyUserSingleLiveEvent)
    }
}