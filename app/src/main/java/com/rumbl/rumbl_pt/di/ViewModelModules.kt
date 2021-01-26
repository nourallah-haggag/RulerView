package com.rumbl.rumbl_pt.di

import com.rumbl.rumbl_pt.features.active_session.ActiveSessionViewModel
import com.rumbl.rumbl_pt.features.auth.AuthViewModel
import com.rumbl.rumbl_pt.features.auth.login.LoginViewModel
import com.rumbl.rumbl_pt.features.auth.password_entry.PasswordEntryViewModel
import com.rumbl.rumbl_pt.features.auth.verification.VerificationViewModel
import com.rumbl.rumbl_pt.features.home.HomeViewModel
import com.rumbl.rumbl_pt.features.notifications.NotificationsViewModel
import com.rumbl.rumbl_pt.features.profile.ProfileViewModel
import com.rumbl.rumbl_pt.features.requests.RequestsViewModel
import com.rumbl.rumbl_pt.features.schedule.ScheculeViewModel
import com.rumbl.rumbl_pt.features.session_details.SessionDetailsViewModel
import com.rumbl.rumbl_pt.features.splash.SplashViewModel
import com.rumbl.rumbl_pt.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Mohamed Shalan on 4/19/20.
 */


val viewmodelModules = module {
    viewModel { AuthViewModel() }
    viewModel { LoginViewModel(repo = get()) }
    viewModel { VerificationViewModel(repo = get()) }
    viewModel { PasswordEntryViewModel(repo = get()) }
    viewModel { HomeViewModel(repo = get()) }
    viewModel { RequestsViewModel(repo = get()) }
    viewModel { ScheculeViewModel(repo = get()) }
    viewModel { NotificationsViewModel(repo = get()) }
    viewModel { ProfileViewModel(repo = get()) }
    viewModel { MainViewModel() }
    viewModel { SessionDetailsViewModel(repo = get()) }
    viewModel { SplashViewModel(repo = get()) }
    viewModel { ActiveSessionViewModel(repo = get()) }
}
