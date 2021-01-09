package com.rumbl.rumbl_pt.repo

import com.rumbl.rumbl_pt.network.services.NotificationsApi

class NotificationsRepo(private val api: NotificationsApi) : IRepo {
    fun getNotifications() = api.getNotifications().map { it.data }
}