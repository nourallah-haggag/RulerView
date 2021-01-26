package com.rumbl.rumbl_pt.features.active_session

import android.os.Bundle
import androidx.core.os.bundleOf
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentSessionInProgressBinding
import com.rumbl.rumbl_pt.enums.SessionTypes
import com.rumbl.rumbl_pt.network.response.SessionsResponse

class ActiveSessionFragment :
    BaseFragment<ActiveSessionViewModel, FragmentSessionInProgressBinding>(
        layoutId = R.layout.fragment_session_in_progress,
        clazz = ActiveSessionViewModel::class
    ) {

    companion object {
        const val SESSION_INFO = "sessionInfo"
        fun passSessionInfo(session: SessionsResponse): Bundle {
            val bundle = bundleOf()
            bundle.putParcelable(SESSION_INFO, session)
            return bundle
        }

    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        requireArguments().getParcelable<SessionsResponse>(SESSION_INFO)?.let { session ->
            binding.apply {
                tvCustomerName.text = session.customer.name
                when (session.session_type) {
                    SessionTypes.CONSULTATION.value -> {
                        tvSessionType.text = getString(R.string.consultation_session)
                    }
                    SessionTypes.TRAINING.value -> {
                        tvSessionType.text = getString(R.string.training_session)
                    }
                }
            }
        }
    }
}