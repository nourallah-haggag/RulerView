package com.rumbl.rumbl_pt.di

import com.rumbl.rumbl_pt.features.auth.AuthViewModel
import com.rumbl.rumbl_pt.features.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 4/19/20.
 */


val viewmodelModules = module {
    viewModel { AuthViewModel() }
    viewModel { LoginViewModel() }
}
