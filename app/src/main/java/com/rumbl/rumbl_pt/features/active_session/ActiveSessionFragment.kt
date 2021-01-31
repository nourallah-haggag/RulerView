package com.rumbl.rumbl_pt.features.active_session

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentSessionInProgressBinding
import com.rumbl.rumbl_pt.enums.SessionStatus
import com.rumbl.rumbl_pt.enums.SessionTypes
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.utils.Dateutils

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
                ivClose.setOnClickListener {
                    findNavController().navigateUp()
                }
                btnEndSession.setOnClickListener {
                    viewmodel.updateSessionStatus(
                        sessionId = session.id,
                        newStatus = SessionStatus.COMPLETED.value
                    )
                }
            }
            constructCountDownTimer(sessionCreationTime = Dateutils.formatDateToTimeStamp(session.statuses.last().created_at))
        }
        observeSessionStatusUpdared()

    }

    private fun constructCountDownTimer(sessionCreationTime: Long) {
        var elapsedSeconds = System.currentTimeMillis() - sessionCreationTime
        binding.tvSessionTimer.text = Dateutils.timeStampToMinutesSesconds(elapsedSeconds)
        object : CountDownTimer(999999999999, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                elapsedSeconds += 1000
                binding.tvSessionTimer.text = Dateutils.timeStampToMinutesSesconds(elapsedSeconds)
            }

            override fun onFinish() {
            }

        }.start()
    }

    private fun observeSessionStatusUpdared() {
        viewmodel.observeUpdatingSessionsSingleLiveEvent().observe(viewLifecycleOwner, Observer {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    showLoading()
                    Toast.makeText(
                        requireContext(),
                        "Session completed successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()
                }
                CommonStatusImp.LOADING -> {
                    showLoading()
                }
                CommonStatusImp.ERROR -> {
                    hideLoading()
                    it.fetchError()?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoading() {
        binding.apply {
            btnEndSession.visibility = View.INVISIBLE
            tvReportAProblem.visibility = View.INVISIBLE
            pbEndSession.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        binding.apply {
            btnEndSession.visibility = View.VISIBLE
            tvReportAProblem.visibility = View.VISIBLE
            pbEndSession.visibility = View.GONE
        }
    }
}