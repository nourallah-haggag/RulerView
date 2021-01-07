package com.rumbl.rumbl_pt.features.profile

import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(
    layoutId = R.layout.fragment_profile,
    clazz = ProfileViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
    }
}