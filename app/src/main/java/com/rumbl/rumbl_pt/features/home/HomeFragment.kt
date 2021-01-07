package com.rumbl.rumbl_pt.features.home

import android.os.Bundle
import android.widget.Toast
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    layoutId = R.layout.fragment_home,
    clazz = HomeViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
        viewmodel.getRequestedAndUpcomingSessions()
        observeSessions()
    }

    private fun observeSessions() {
        viewmodel.observeSessionsEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    val homeModelsList = mutableListOf<HomeViewModel>()
                    it.fetchData()?.let { sessions ->
                        sessions.first.apply {

                        }
                        sessions.second.apply {

                        }
                    }
                }
                CommonStatusImp.LOADING -> {
                }
                CommonStatusImp.ERROR -> {
                    it.fetchError()?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}