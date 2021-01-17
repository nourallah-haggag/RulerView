package com.rumbl.rumbl_pt.features.requests

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentRequestsBinding
import com.rumbl.rumbl_pt.features.home.home_list.HomeItemsInteractionListener
import com.rumbl.rumbl_pt.features.home.home_list.latest_requests.LatestRequestsAdapter
import com.rumbl.rumbl_pt.features.session_details.SessionDetailsFragment
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import kotlinx.android.synthetic.main.fragment_requests.*

class RequestsFragment : BaseFragment<RequestsViewModel, FragmentRequestsBinding>(
    clazz = RequestsViewModel::class,
    layoutId = R.layout.fragment_requests
), HomeItemsInteractionListener {
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
                        if (sessions.isNotEmpty()) {
                            val adapter = LatestRequestsAdapter(sessions, this)
                            rv_requested_sessions.adapter = adapter
                        } else {
                            binding.apply {
                                viewEmptySessions.visibility = View.VISIBLE
                                rvRequestedSessions.visibility = View.GONE
                            }
                        }
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

    override fun onAllLatestSessionsClicked() {
    }

    override fun onAllUpcomingSessionsClicked() {
    }

    override fun onSessionItemClicked(session: SessionsResponse) {
        findNavController().navigate(
            R.id.action_requests_to_session_details,
            SessionDetailsFragment.passSessionInfo(
                session,
                SessionDetailsFragment.SessionDetailsType.NORMAL_SESSION_DETAILS
            )
        )
    }

    override fun onAcceptSessionClicked() {
    }
}