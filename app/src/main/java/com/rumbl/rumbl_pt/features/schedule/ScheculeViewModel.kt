package com.rumbl.rumbl_pt.features.schedule

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.repo.SessionsRepo
import java.util.concurrent.TimeUnit

class ScheculeViewModel(private val repo:SessionsRepo): BaseViewModel() {
    private val sessionsSingleLiveEvent: SingleLiveEvent<IResult<List<SessionsResponse>>> by lazy {
        SingleLiveEvent()
    }

    fun observeSessions(): SingleLiveEvent<IResult<List<SessionsResponse>>> =
        sessionsSingleLiveEvent

    fun fetchSessionsByDate(date:String) {
        repo.getSessionsByDate(date).delay(500,TimeUnit.MILLISECONDS).execute(sessionsSingleLiveEvent)
    }
}