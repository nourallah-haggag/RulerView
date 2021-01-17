package com.rumbl.rumbl_pt.network.services

import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.network.response.base.BaseWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface SessionsApi {

    @GET("pt/get-sessions")
    fun getSessionsByStatus(@Query("status") status: Int): Single<BaseWrapper<List<SessionsResponse>>>

    @GET("pt/get-sessions")
    fun getSessionsByDate(@Query("date") date: String): Single<BaseWrapper<List<SessionsResponse>>>

    @PATCH("pt/accept-session")
    fun acceptSession(@Query("id") id: Int): Single<BaseWrapper<Any>>
}