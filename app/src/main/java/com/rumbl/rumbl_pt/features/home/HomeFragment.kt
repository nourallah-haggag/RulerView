package com.rumbl.rumbl_pt.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentHomeBinding
import com.rumbl.rumbl_pt.features.home.home_list.HomeItemsAdapter
import com.rumbl.rumbl_pt.features.home.home_list.HomeItemsInteractionListener
import com.rumbl.rumbl_pt.features.session_details.SessionDetailsFragment
import com.rumbl.rumbl_pt.models.HeaderItem
import com.rumbl.rumbl_pt.models.IHomeScreenModel
import com.rumbl.rumbl_pt.models.LatestSessionsRequests
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    layoutId = R.layout.fragment_home,
    clazz = HomeViewModel::class
), HomeItemsInteractionListener {
    override fun onCreateInit(savedInstanceState: Bundle?) {
        viewmodel.getRequestedAndUpcomingSessions()
        observeSessions()
        tv_trainer_name.text = getString(R.string.trainer_name, viewmodel.getTrainerName())
    }

    private fun observeSessions() {
        viewmodel.observeSessionsEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    lottie_loading_anim.visibility = View.GONE
                    rv_home_sessions.visibility = View.VISIBLE
                    tv_trainer_name.visibility = View.VISIBLE
                    val homeModelsList = mutableListOf<IHomeScreenModel>()
                    it.fetchData()?.let { sessions ->
                        sessions.first.apply {
                            homeModelsList.add(LatestSessionsRequests(this))
                        }
                        sessions.second.apply {
                            if (this.isNotEmpty()) {
                                homeModelsList.add(HeaderItem)
                                homeModelsList.addAll(this)
                            }
                        }
                        val homeItemsAdapter = HomeItemsAdapter(homeModelsList, this)
                        rv_home_sessions.adapter = homeItemsAdapter
                    }
                }
                CommonStatusImp.LOADING -> {
                    lottie_loading_anim.visibility = View.VISIBLE
                    rv_home_sessions.visibility = View.GONE
                    tv_trainer_name.visibility = View.GONE
                }
                CommonStatusImp.ERROR -> {
                    lottie_loading_anim.visibility = View.GONE
                    rv_home_sessions.visibility = View.VISIBLE
                    tv_trainer_name.visibility = View.VISIBLE
                    it.fetchError()?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onAllLatestSessionsClicked() {
        findNavController().navigate(R.id.requestsFragment)

    }

    override fun onAllUpcomingSessionsClicked() {

    }

    override fun onSessionItemClicked(session: SessionsResponse) {
        findNavController().navigate(
            R.id.action_home_to_session_details,
            SessionDetailsFragment.passSessionInfo(
                session,
                SessionDetailsFragment.SessionDetailsType.NORMAL_SESSION_DETAILS
            )
        )
    }

    override fun onAcceptSessionClicked() {

    }
}