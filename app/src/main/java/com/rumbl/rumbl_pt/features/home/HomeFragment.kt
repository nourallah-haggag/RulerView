package com.rumbl.rumbl_pt.features.home

import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    layoutId = R.layout.fragment_home,
    clazz = HomeViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
    }
}