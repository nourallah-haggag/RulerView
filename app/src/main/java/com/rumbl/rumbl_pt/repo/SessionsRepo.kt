package com.rumbl.rumbl_pt.repo

import com.rumbl.rumbl_pt.network.services.SessionsApi

class SessionsRepo(private val api: SessionsApi) : IRepo {

    private fun getSessionsByStatus(status: Int) = api.getSessionsByStatus(status).map {
        it.data
    }

    fun getRequestedAndUpcomingSessions() =
        getSessionsByStatus(1).zipWith(getSessionsByStatus(2), { t1, t2 ->
            Pair(t1, t2)
        })

    fun getRequestedSessions() = getSessionsByStatus(1)

    fun getSessionsByDate(date: String) = api.getSessionsByDate(date).map {
        it.data
    }

    fun acceptSession(id: Int) = api.acceptSession(id).map { it.data }

    fun rejectSession(id: Int) = api.rejectSession(id).map { it.data }
}