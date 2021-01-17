package com.rumbl.rumbl_pt.features.session_details

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.repo.SessionsRepo

class SessionDetailsViewModel(private val repo: SessionsRepo) : BaseViewModel() {
    private val acceptSessionSingleLiveEvent: SingleLiveEvent<IResult<Any>> by lazy {
        SingleLiveEvent()
    }

    fun observeAccpetSessionSingleLiveEvent(): SingleLiveEvent<IResult<Any>> =
        acceptSessionSingleLiveEvent

    fun acceptSession(sessionId: Int) {
        repo.acceptSession(sessionId).execute(acceptSessionSingleLiveEvent)
    }
}