package com.rumbl.rumbl_pt.features.requests

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentRequestsBinding
import com.rumbl.rumbl_pt.features.home.home_list.latest_requests.LatestRequestsAdapter
import kotlinx.android.synthetic.main.fragment_requests.*

class RequestsFragment : BaseFragment<RequestsViewModel, FragmentRequestsBinding>(
    clazz = RequestsViewModel::class,
    layoutId = R.layout.fragment_requests
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
        viewmodel.fetchRequestedSessions()
        observeRequestedSessions()
    }

    private fun observeRequestedSessions() {
        viewmodel.observeRequestedSessions().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    lottie_loading_anim.visibility = View.GONE
                    tv_request_screen_title.visibility = View.VISIBLE
                    rv_requested_sessions.visibility = View.VISIBLE
                    it.fetchData()?.let { sessions ->
                        val adapter = LatestRequestsAdapter(sessions)
                        rv_requested_sessions.adapter = adapter
                    }
                }
                CommonStatusImp.LOADING -> {
                    lottie_loading_anim.visibility = View.VISIBLE
                    tv_request_screen_title.visibility = View.GONE
                    rv_requested_sessions.visibility = View.GONE
                }
                CommonStatusImp.ERROR -> {
                    lottie_loading_anim.visibility = View.GONE
                    tv_request_screen_title.visibility = View.VISIBLE
                    rv_requested_sessions.visibility = View.VISIBLE
                    it.fetchError()?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}