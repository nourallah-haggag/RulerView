package com.rumbl.rumbl_pt.features.session_details

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.services.ImageLoadingService
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentSessionDetailsBinding
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import com.rumbl.rumbl_pt.utils.Dateutils
import kotlinx.android.synthetic.main.fragment_session_details.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class SessionDetailsFragment :
    BaseFragment<SessionDetailsViewModel, FragmentSessionDetailsBinding>(
        layoutId = R.layout.fragment_session_details,
        clazz = SessionDetailsViewModel::class
    ), KoinComponent {

    private val imageLoadingService: ImageLoadingService by inject()

    companion object {
        const val SESSION_INFO = "session_info"
        const val SESSION_DETAILS_TYPE = "session_details_type"
        fun passSessionInfo(
            sessionInfo: SessionsResponse,
            sessionDetailsType: SessionDetailsType
        ): Bundle {
            val bundle = bundleOf()
            bundle.apply {
                putParcelable(SESSION_INFO, sessionInfo)
                putSerializable(SESSION_DETAILS_TYPE, sessionDetailsType)
            }
            return bundle
        }
    }

    enum class SessionDetailsType {
        NORMAL_SESSION_DETAILS, NOTIFICATION_SESSION_DETAILS
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        requireArguments().getParcelable<SessionsResponse>(SESSION_INFO)?.let { sessionInfo ->
            sessionInfo.customer.avatar?.let { photo ->
                imageLoadingService.loadCenterCropImageWithPlaceholder(
                    requireContext(),
                    photo,
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_rumbl_logo)!!,
                    iv_session_photo
                )
            }
            tv_pt_name.text = sessionInfo.customer.name
            when (sessionInfo.customer.gender) {
                1 -> {
                    iv_gender.setImageResource(R.drawable.ic_male)
                }
                2 -> {
                    iv_gender.setImageResource(R.drawable.ic_female)
                }
            }
            // TODO: to be changed when ready from back end
            chip_pt_level.text = "Lose Weight"
            chip_pt_level.isClickable = false
            sessionInfo.customer.apply {
                weight?.let { weight ->
                    tv_weight.text = weight.toString()
                }
                height?.let { height ->
                    tv_height.text = height.toString()
                }
                bmi?.let { bmi ->
                    tv_bmi.text = bmi.toString()
                }
            }
            when (sessionInfo.session_type) {
                1 -> {
                    tv_session_type.text = getString(R.string.training_session)
                }
                2 -> {
                    tv_session_type.text = getString(R.string.consultation_session)
                }
            }
            tv_no_of_sessions_value.text = sessionInfo.num_of_sessions_with_customer.toString()
            tv_location_value.text = sessionInfo.customer_address
            tv_date_time_value.text = Dateutils.formatToLongDate(sessionInfo.date)
            tv_time_value.text = sessionInfo.time_slot.from
            tv_navigate.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tv_no_of_sessions_label.text =
                getString(R.string.no_of_sessions, sessionInfo.customer.name)
            iv_back.setOnClickListener {
                findNavController().navigateUp()
            }
            binding.btnAcceptSession.setOnClickListener {
                viewmodel.acceptSession(sessionInfo.id)
            }
            binding.tvDecline.setOnClickListener {
                viewmodel.rejectSession(sessionInfo.id)
            }
        }
        requireArguments().getSerializable(SESSION_DETAILS_TYPE)?.let { sessionDetailsType ->
            when (sessionDetailsType) {
                SessionDetailsType.NOTIFICATION_SESSION_DETAILS -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.GONE
                        tvDecline.visibility = View.GONE
                    }

                }
                SessionDetailsType.NORMAL_SESSION_DETAILS -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.VISIBLE
                        tvDecline.visibility = View.VISIBLE
                    }

                }
                else -> throw IllegalStateException("No such state for session details")
            }
        }
        observeAccpetSessionSingleLiveEvent()
        observeRejectSessionSingleLiveEvent()
    }

    private fun observeAccpetSessionSingleLiveEvent() {
        viewmodel.observeAccpetSessionSingleLiveEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.VISIBLE
                        tvDecline.visibility = View.VISIBLE
                        progressAcceptDecline.visibility = View.GONE
                    }
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.session_booked_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()
                }
                CommonStatusImp.LOADING -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.INVISIBLE
                        tvDecline.visibility = View.INVISIBLE
                        progressAcceptDecline.visibility = View.VISIBLE
                    }
                }
                CommonStatusImp.ERROR -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.VISIBLE
                        tvDecline.visibility = View.VISIBLE
                        progressAcceptDecline.visibility = View.GONE
                    }
                    it.fetchError()?.let { error ->
                        Toast.makeText(
                            requireContext(),
                            error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun observeRejectSessionSingleLiveEvent() {
        viewmodel.observeRejectessionSingleLiveEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.VISIBLE
                        tvDecline.visibility = View.VISIBLE
                        progressAcceptDecline.visibility = View.GONE
                    }
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.session_rejected),
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()
                }
                CommonStatusImp.LOADING -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.INVISIBLE
                        tvDecline.visibility = View.INVISIBLE
                        progressAcceptDecline.visibility = View.VISIBLE
                    }
                }
                CommonStatusImp.ERROR -> {
                    binding.apply {
                        btnAcceptSession.visibility = View.VISIBLE
                        tvDecline.visibility = View.VISIBLE
                        progressAcceptDecline.visibility = View.GONE
                    }
                    it.fetchError()?.let { error ->
                        Toast.makeText(
                            requireContext(),
                            error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}