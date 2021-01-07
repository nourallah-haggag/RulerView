package com.rumbl.rumbl_pt.features.schedule

import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentScheduleBinding

class ScheduleFragment : BaseFragment<ScheculeViewModel, FragmentScheduleBinding>(
    layoutId = R.layout.fragment_schedule,
    clazz = ScheculeViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
    }
}