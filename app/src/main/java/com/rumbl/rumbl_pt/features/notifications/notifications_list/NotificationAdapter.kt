package com.rumbl.rumbl_pt.features.notifications.notifications_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.models.Notification

class NotificationAdapter(
    private val notifications: List<Notification>,
    private val notificationsInteractionListener: NotificationsInteractionListener
) :
    RecyclerView.Adapter<NotificationsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        holder.bind(notifications[position], notificationsInteractionListener)
    }

    override fun getItemCount(): Int = notifications.size
}

interface NotificationsInteractionListener {
    fun onNotificationItemClicked(item: Notification)
}