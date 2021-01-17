package com.rumbl.rumbl_pt.features.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.repo.SessionsRepo
import java.util.concurrent.TimeUnit

class ScheculeViewModel(private val repo:SessionsRepo): BaseViewModel() {
    private val sessionsSingleLiveData: MutableLiveData<IResult<List<SessionsResponse>>> by lazy {
        MutableLiveData()
    }

    fun observeSessions(): LiveData<IResult<List<SessionsResponse>>> =
        sessionsSingleLiveData

    fun fetchSessionsByDate(date:String) {
        repo.getSessionsByDate(date).delay(500,TimeUnit.MILLISECONDS).execute(sessionsSingleLiveData)
    }
}