package com.rumbl.rumbl_pt.features.notifications

import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentNotificationsBinding

class NotificationsFragment : BaseFragment<NotificationsViewModel, FragmentNotificationsBinding>(
    layoutId = R.layout.fragment_notifications,
    clazz = NotificationsViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
    }
}