package com.rumbl.rumbl_pt.features.notifications

import android.os.Bundle
import android.widget.Toast
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentNotificationsBinding

class NotificationsFragment : BaseFragment<NotificationsViewModel, FragmentNotificationsBinding>(
    layoutId = R.layout.fragment_notifications,
    clazz = NotificationsViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
        viewmodel.fetchNotifications()
        observeNotification()
    }

    private fun observeNotification() {
        viewmodel.observeNotificationsSingleLiveEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                }
                CommonStatusImp.LOADING -> {
                }
                CommonStatusImp.ERROR -> {
                    it.fetchError()?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}