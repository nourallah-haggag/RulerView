package com.rumbl.rumbl_pt.features.auth.verification

import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentVerificationBinding

class VerificationFragment : BaseFragment<VerificationViewModel, FragmentVerificationBinding>(
    layoutId = R.layout.fragment_verification,
    clazz = VerificationViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
    }
}