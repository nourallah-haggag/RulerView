package com.rumbl.rumbl_pt.features.home

import android.os.Bundle
import android.view.View
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
import com.rumbl.rumbl_pt.models.NoSessionsItem
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.utils.DialogsUtils
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
                            if (this.isEmpty()) {
                                homeModelsList.add(NoSessionsItem)
                            }
                        }
                        sessions.second.apply {
                            homeModelsList.add(HeaderItem)
                            homeModelsList.addAll(this)
                            if (this.isEmpty()) {
                                homeModelsList.add(NoSessionsItem)
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
                        DialogsUtils.showBlockingDialog(
                            context = requireContext(),
                            view = R.layout.layout_error_dialog,
                            message = error,
                            retryAction = {
                                viewmodel.getRequestedAndUpcomingSessions()
                            })
                    }

                }
            }
        })
    }

    override fun onAllLatestSessionsClicked() {
        findNavController().navigate(R.id.requestsFragment)

    }

    override fun onAllUpcomingSessionsClicked() {
        findNavController().navigate(R.id.requestsFragment)
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