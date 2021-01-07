package com.rumbl.rumbl_pt.network.services

import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.network.response.base.BaseWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SessionsApi {

    @GET("pt/get-sessions")
    fun getSessionsByStatus(@Query("status") status: Int): Single<BaseWrapper<List<SessionsResponse>>>
}