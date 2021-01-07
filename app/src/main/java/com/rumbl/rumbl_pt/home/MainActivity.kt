package com.rumbl.rumbl_pt.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.activities.BaseActivity
import com.rumbl.rumbl_pt.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(starter)
        }
    }

    private lateinit var navController: NavController


    override val layoutId: Int = R.layout.activity_main

    override fun onCreateInit(savedInstance: Bundle?) {
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNav.setupWithNavController(navController)
    }

    private fun showBottomNavView() {
        binding.bottomNav.visibility = View.VISIBLE

    }

    private fun hideBottomNavView() {
        binding.bottomNav.visibility = View.GONE
    }
}