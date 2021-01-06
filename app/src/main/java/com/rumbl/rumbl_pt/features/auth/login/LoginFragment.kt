package com.rumbl.rumbl_pt.features.auth.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.network.Constants
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentLoginBinding
import com.rumbl.rumbl_pt.features.auth.verification.VerificationFragment
import kotlinx.android.synthetic.main.view_phone_field.view.*

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(
    layoutId = R.layout.fragment_login,
    clazz = LoginViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
        observeSendPhoneEvent()
        binding.btnLogin.setOnClickListener {
            viewmodel.sendPhone(binding.viewEtPhone.et_phone.text.toString())
        }
    }

    private fun observeSendPhoneEvent() {
        viewmodel.observeLoginSingleLiveEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    binding.apply {
                        progressSendPhone.visibility = View.GONE
                        btnLogin.visibility = View.VISIBLE
                    }

                }
                CommonStatusImp.LOADING -> {
                    binding.apply {
                        progressSendPhone.visibility = View.VISIBLE
                        btnLogin.visibility = View.INVISIBLE
                    }

                }
                CommonStatusImp.ERROR -> {
                    binding.apply {
                        progressSendPhone.visibility = View.GONE
                        btnLogin.visibility = View.VISIBLE
                    }
                    it.fetchError()?.let { error ->
                        // check on error codes and behave accordingly
                        when (error) {
                            Constants.NO_ACCOUNT, Constants.ACCOUNT_INACTACTIVE, Constants.DIDNOT_SET_PASSWORD -> {
                                findNavController().navigate(
                                    R.id.action_login_to_verification,
                                    VerificationFragment.passPhoneNumber(phone = binding.viewEtPhone.et_phone.text.toString())
                                )
                            }
                            Constants.WRONG_PHONE_OR_PASSWORD -> {
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.wrong_phone_or_password),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.unknown_error_occurred),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    }
                }
            }
        })
    }
}