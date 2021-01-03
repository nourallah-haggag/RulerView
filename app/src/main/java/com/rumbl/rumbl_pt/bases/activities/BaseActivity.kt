package com.rumbl.rumbl_pt.bases.activities

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rumbl.rumbl_pt.bases.LocaleManager
import com.rumbl.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

abstract class BaseActivity<ViewModel : BaseViewModel, Binding : ViewDataBinding>(clazz: KClass<ViewModel>) :
    AppCompatActivity(), IActivity {

    protected lateinit var binding: Binding
    protected val viewmodel by viewModel(clazz = clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewmodel)
        viewmodel.ioExceptionMessage = getString(R.string.txt_io_exception_message)
        viewmodel.socketTimeoutExceptionMessage =
            getString(R.string.txt_socket_timout_exception_message)
        viewmodel.internalServerErrorMessage = getString(R.string.txt_general_error)
        viewmodel.startLogic()
        onCreateInit(savedInstanceState)
    }

    fun enableFullScreenMode() {
        window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.getLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LocaleManager.getLocale(this)
        super.onConfigurationChanged(newConfig)
    }
}
