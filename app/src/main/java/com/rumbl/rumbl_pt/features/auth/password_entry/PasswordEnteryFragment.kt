package com.rumbl.rumbl_pt.features.auth.password_entry

import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentPasswordEntryBinding

class PasswordEnteryFragment : BaseFragment<PasswordEntryViewModel, FragmentPasswordEntryBinding>(
    layoutId = R.layout.fragment_password_entry,
    clazz = PasswordEntryViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
    }
}