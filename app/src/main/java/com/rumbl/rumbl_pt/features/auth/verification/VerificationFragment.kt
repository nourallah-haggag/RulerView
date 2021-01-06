package com.rumbl.rumbl_pt.features.auth.verification

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentVerificationBinding
import kotlinx.android.synthetic.main.fragment_verification.*
import kotlinx.android.synthetic.main.view_verification_field.*
import kotlinx.android.synthetic.main.view_verification_field.view.*

class VerificationFragment : BaseFragment<VerificationViewModel, FragmentVerificationBinding>(
    layoutId = R.layout.fragment_verification,
    clazz = VerificationViewModel::class
) {

    companion object {
        const val PHONE_KEY = "phone"
        fun passPhoneNumber(phone: String): Bundle {
            val bundle = bundleOf()
            bundle.putString(PHONE_KEY, phone)
            return bundle
        }
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            if (areFieldsValid()) {
                requireArguments().getString(PHONE_KEY)?.let { phoneNumber ->
                    viewmodel.verifyUser(
                        code = binding.viewVerification.et_pin.text.toString(),
                        password = binding.etPassword.text.toString(),
                        phone = phoneNumber
                    )
                }
            }
        }
        observeVerificationEvent()
        listenToPinNoEntry()
    }

    private fun listenToPinNoEntry() {
        et_pin.doOnTextChanged { text, _, _, _ ->
            if (text?.length == 4) {
                btn_login.isEnabled = true
                closeKeyboard()
            } else {
                btn_login.isEnabled = false
            }
        }
    }

    private fun areFieldsValid(): Boolean {
        if (binding.etPassword.text.toString()
                .isNotBlank() || binding.viewVerification.et_pin.text.toString().isNotBlank()
        ) {
            return if (binding.etPassword.length() < 8) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.password_length_error),
                    Toast.LENGTH_SHORT
                ).show()
                false
            } else {
                true
            }
        }
        Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT)
            .show()
        return false
    }

    private fun observeVerificationEvent() {
        viewmodel.observeVerifyUserEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {

                CommonStatusImp.SUCCESS -> {
                    binding.apply {
                        btnLogin.visibility = View.VISIBLE
                        progressVerification.visibility = View.GONE
                    }
                }
                CommonStatusImp.LOADING -> {
                    binding.apply {
                        btnLogin.visibility = View.INVISIBLE
                        progressVerification.visibility = View.VISIBLE
                    }
                }
                CommonStatusImp.ERROR -> {
                    binding.apply {
                        btnLogin.visibility = View.VISIBLE
                        progressVerification.visibility = View.GONE
                    }
                }
            }
        })
    }
}