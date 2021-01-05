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
                    it.fetchData()?.trainer?.let {
                        //TODO: check response with zekry
                        findNavController().navigate(R.id.action_login_to_password_entry)
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
                        if (error == Constants.UNVERIFIED_USER_CODE) {
                            findNavController().navigate(R.id.action_login_to_verification)
                        } else {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }
}