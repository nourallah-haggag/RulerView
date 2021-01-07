package com.rumbl.rumbl_pt.features.auth.password_entry

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentPasswordEntryBinding
import com.rumbl.rumbl_pt.home.MainActivity
import com.rumbl.rumbl_pt.network.Constants
import kotlinx.android.synthetic.main.fragment_password_entry.*

class PasswordEnteryFragment : BaseFragment<PasswordEntryViewModel, FragmentPasswordEntryBinding>(
    layoutId = R.layout.fragment_password_entry,
    clazz = PasswordEntryViewModel::class
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
        btn_login.setOnClickListener {
            if (isPasswordValid()) {
                requireArguments().getString(PHONE_KEY)?.let { phone ->
                    viewmodel.loginTrainer(
                        phone = phone,
                        password = binding.etPassword.text.toString()
                    )
                }
            }
        }
        observeLoginEvent()
    }

    private fun isPasswordValid(): Boolean {
        return if (binding.etPassword.text.toString()
                .isBlank() || binding.etPassword.text.toString().length < 5
        ) {
            Toast.makeText(requireContext(), R.string.password_length_error, Toast.LENGTH_SHORT)
                .show()
            false
        } else {
            true
        }
    }

    private fun observeLoginEvent() {
        viewmodel.observeLoginSingleLiveEvent().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    binding.apply {
                        progressLogin.visibility = View.GONE
                        btnLogin.visibility = View.VISIBLE
                    }
                    MainActivity.start(requireContext())
                }
                CommonStatusImp.LOADING -> {
                    binding.apply {
                        progressLogin.visibility = View.VISIBLE
                        btnLogin.visibility = View.INVISIBLE
                    }
                }
                CommonStatusImp.ERROR -> {
                    binding.apply {
                        progressLogin.visibility = View.GONE
                        btnLogin.visibility = View.VISIBLE
                    }
                    it.fetchError()?.let { error ->
                        // check on error codes and behave accordingly
                        when (error) {
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