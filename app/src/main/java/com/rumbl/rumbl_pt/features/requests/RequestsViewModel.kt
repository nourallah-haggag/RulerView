package com.rumbl.rumbl_pt.features.requests

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.repo.SessionsRepo

class RequestsViewModel(private val repo: SessionsRepo) : BaseViewModel() {
    private val requestedSessionsSingleLiveEvent: SingleLiveEvent<IResult<List<SessionsResponse>>> by lazy {
        SingleLiveEvent()
    }

    fun observeRequestedSessions(): SingleLiveEvent<IResult<List<SessionsResponse>>> =
        requestedSessionsSingleLiveEvent

    fun fetchRequestedSessions() {
        repo.getRequestedSessions().execute(requestedSessionsSingleLiveEvent)
    }
}