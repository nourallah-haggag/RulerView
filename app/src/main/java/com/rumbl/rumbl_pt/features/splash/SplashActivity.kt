package com.rumbl.rumbl_pt.features.splash

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.activities.BaseActivity
import com.rumbl.rumbl_pt.databinding.ActivitySplashBinding
import com.rumbl.rumbl_pt.features.auth.AuthActivity
import com.rumbl.rumbl_pt.home.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(clazz = SplashViewModel::class) {

    override val layoutId: Int = R.layout.activity_splash

    override fun onCreateInit(savedInstance: Bundle?) {
        lifecycleScope.launch {
            delay(1000)
            if (viewmodel.hasValidSession()) {
                MainActivity.start(this@SplashActivity)
            } else {
                AuthActivity.start(context = this@SplashActivity)
            }

        }
    }
}