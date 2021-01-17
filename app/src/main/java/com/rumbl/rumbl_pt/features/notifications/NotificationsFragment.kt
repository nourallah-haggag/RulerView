package com.rumbl.rumbl_pt.features.notifications

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentNotificationsBinding
import com.rumbl.rumbl_pt.features.notifications.notifications_list.NotificationAdapter
import com.rumbl.rumbl_pt.features.notifications.notifications_list.NotificationsInteractionListener
import com.rumbl.rumbl_pt.features.session_details.SessionDetailsFragment
import com.rumbl.rumbl_pt.models.Notification
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : BaseFragment<NotificationsViewModel, FragmentNotificationsBinding>(
    layoutId = R.layout.fragment_notifications,
    clazz = NotificationsViewModel::class
), NotificationsInteractionListener {
    override fun onCreateInit(savedInstanceState: Bundle?) {
        viewmodel.fetchNotifications()
        observeNotification()
    }

    private fun observeNotification() {
        viewmodel.observeNotificationsSingleLiveEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    lottie_loading_anim.visibility = View.GONE
                    tv_notification_title.visibility = View.VISIBLE
                    rv_notifications.visibility = View.VISIBLE
                    it.fetchData()?.let { notifications ->
                        val notificationsAdapter = NotificationAdapter(notifications, this)
                        rv_notifications.adapter = notificationsAdapter
                    }
                }
                CommonStatusImp.LOADING -> {
                    lottie_loading_anim.visibility = View.VISIBLE
                    tv_notification_title.visibility = View.GONE
                    rv_notifications.visibility = View.GONE
                }
                CommonStatusImp.ERROR -> {
                    lottie_loading_anim.visibility = View.GONE
                    tv_notification_title.visibility = View.VISIBLE
                    rv_notifications.visibility = View.VISIBLE
                    it.fetchError()?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onNotificationItemClicked(item: Notification) {
        findNavController().navigate(
            R.id.action_notification_to_session_details,
            SessionDetailsFragment.passSessionInfo(
                item.session,
                SessionDetailsFragment.SessionDetailsType.NOTIFICATION_SESSION_DETAILS
            )
        )
    }
}