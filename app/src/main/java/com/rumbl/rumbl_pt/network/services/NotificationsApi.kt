package com.rumbl.rumbl_pt.network.services

import com.rumbl.rumbl_pt.models.Notification
import com.rumbl.rumbl_pt.network.response.base.BaseWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NotificationsApi {

    @GET("pt/notifications")
    fun getNotifications(): Single<BaseWrapper<List<Notification>>>
}