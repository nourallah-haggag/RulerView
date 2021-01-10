package com.rumbl.rumbl_pt.features.profile

import com.rumbl.rumbl_pt.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.network.response.Trainer
import com.rumbl.rumbl_pt.repo.AuthRepo

class ProfileViewModel(private val repo: AuthRepo) : BaseViewModel() {
    fun getTrainerData() = repo.getSessionService().getUser(Trainer::class.java)
}