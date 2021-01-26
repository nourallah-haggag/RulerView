package com.rumbl.rumbl_pt.features.session_details

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.repo.SessionsRepo

class SessionDetailsViewModel(private val repo: SessionsRepo) : BaseViewModel() {
    private val acceptSessionSingleLiveEvent: SingleLiveEvent<IResult<Any>> by lazy {
        SingleLiveEvent()
    }

    private val rejectSessionSingleLiveEvent: SingleLiveEvent<IResult<Any>> by lazy {
        SingleLiveEvent()
    }

    private val updateSessionSingleLiveEvent: SingleLiveEvent<IResult<SessionsResponse>> by lazy {
        SingleLiveEvent()
    }

    fun observeUpdatingSessionsSingleLiveEvent(): SingleLiveEvent<IResult<SessionsResponse>> =
        updateSessionSingleLiveEvent

    fun observeAccpetSessionSingleLiveEvent(): SingleLiveEvent<IResult<Any>> =
        acceptSessionSingleLiveEvent

    fun observeRejectessionSingleLiveEvent(): SingleLiveEvent<IResult<Any>> =
        rejectSessionSingleLiveEvent

    fun acceptSession(sessionId: Int) {
        repo.acceptSession(sessionId).execute(acceptSessionSingleLiveEvent)
    }

    fun rejectSession(sessionId: Int) {
        repo.rejectSession(sessionId).execute(rejectSessionSingleLiveEvent)
    }

    fun updateSessionStatus(sessionId: Int, newStatus: Int) {
        repo.updateSessionStatus(sessionId, newStatus).execute(updateSessionSingleLiveEvent)

    }
}