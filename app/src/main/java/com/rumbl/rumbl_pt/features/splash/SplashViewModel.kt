package com.rumbl.rumbl_pt.features.splash

import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.repo.AuthRepo

class SplashViewModel(private val repo: AuthRepo) : BaseViewModel() {
    fun hasValidSession() = repo.getSessionService().hasValidSession()
}