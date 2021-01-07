package com.rumbl.rumbl_pt.features.requests

import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentRequestsBinding

class RequestsFragment : BaseFragment<RequestsViewModel, FragmentRequestsBinding>(
    clazz = RequestsViewModel::class,
    layoutId = R.layout.fragment_requests
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
    }
}