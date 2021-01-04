package com.rumbl.rumbl_pt.features.auth.login

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.databinding.FragmentLoginBinding
import com.rumbl.rumbl_pt.features.auth.login.LoginViewModel

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(
    layoutId = R.layout.fragment_login,
    clazz = LoginViewModel::class
) {
    override fun onCreateInit(savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_verification)
        }
    }
}