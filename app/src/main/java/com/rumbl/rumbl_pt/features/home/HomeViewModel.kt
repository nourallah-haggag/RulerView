package com.rumbl.rumbl_pt.features.home

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.network.response.Trainer
import com.rumbl.rumbl_pt.repo.SessionsRepo

class HomeViewModel(private val repo: SessionsRepo) : BaseViewModel() {
    private val sessionsSingleLiveEvent: SingleLiveEvent<IResult<Pair<List<SessionsResponse>, List<SessionsResponse>>>> by lazy {
        SingleLiveEvent()
    }

    fun getTrainerName() = repo.getSessionService().getUser(Trainer::class.java)?.name

    fun observeSessionsEvent(): SingleLiveEvent<IResult<Pair<List<SessionsResponse>, List<SessionsResponse>>>> =
        sessionsSingleLiveEvent

    fun getRequestedAndUpcomingSessions() {
        repo.getRequestedAndUpcomingSessions().execute(sessionsSingleLiveEvent)
    }
}