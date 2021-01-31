package com.rumbl.rumbl_pt.features.active_session

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.repo.SessionsRepo

class ActiveSessionViewModel(private val repo: SessionsRepo) : BaseViewModel() {


    private val updateSessionSingleLiveEvent: SingleLiveEvent<IResult<SessionsResponse>> by lazy {
        SingleLiveEvent()
    }

    fun observeUpdatingSessionsSingleLiveEvent(): SingleLiveEvent<IResult<SessionsResponse>> =
        updateSessionSingleLiveEvent

    fun updateSessionStatus(sessionId: Int, newStatus: Int) {
        repo.updateSessionStatus(sessionId, newStatus).execute(updateSessionSingleLiveEvent)
    }

}