package com.rumbl.rumbl_pt.features.notifications

import com.rumbl.rumbl_pt.bases.SingleLiveEvent
import com.rumbl.rumbl_pt.bases.states.IResult
import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.models.Notification
import com.rumbl.rumbl_pt.repo.NotificationsRepo

class NotificationsViewModel(private val repo: NotificationsRepo) : BaseViewModel() {
    private val notificationsSingleLiveEvent: SingleLiveEvent<IResult<List<Notification>>> by lazy {
        SingleLiveEvent()
    }

    fun observeNotificationsSingleLiveEvent(): SingleLiveEvent<IResult<List<Notification>>> =
        notificationsSingleLiveEvent

    fun fetchNotifications() {
        repo.getNotifications().execute(notificationsSingleLiveEvent)
    }
}