package com.rumbl.rumbl_pt.features.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.activities.BaseActivity
import com.rumbl.rumbl_pt.databinding.ActivityAuthBinding

class AuthActivity :
    BaseActivity<AuthViewModel, ActivityAuthBinding>(clazz = AuthViewModel::class) {

    companion object {
        fun start(context: Context, shouldClearBackStack: Boolean = true) {
            context.startActivity(Intent(context, AuthActivity::class.java).apply {
                if (shouldClearBackStack)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }

    override val layoutId: Int = R.layout.activity_auth


    override fun onCreateInit(savedInstance: Bundle?) {

    }
}