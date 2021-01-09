package com.rumbl.rumbl_pt.features.notifications.notifications_list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.services.ImageLoadingService
import com.rumbl.rumbl_pt.models.Notification
import kotlinx.android.synthetic.main.item_notification.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotificationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {
    val imageLoadingService: ImageLoadingService by inject()
    fun bind(item: Notification) {
        with(itemView)
        {
            tv_notification_title.text = item.title
            tv_notification_body.text = item.content
            when (item.type) {
                // cancelled
                2 -> {
                    iv_notification.setImageResource(R.drawable.ic_cancelled_session)
                }
                // reminder
                3 -> {
                    iv_notification.setImageResource(R.drawable.ic_notification)
                }
                else -> {
                    item.session.customer.avatar?.let { image ->
                        imageLoadingService.loadCircleImageWithPlaceholder(
                            context,
                            image,
                            ContextCompat.getDrawable(context, R.drawable.ic_rumbl_logo)!!,
                            iv_notification
                        )
                    }
                }
            }
        }
    }
}