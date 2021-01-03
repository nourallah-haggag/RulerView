package com.rumbl.rumbl_pt.bases.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rumbl.bases.viewmodel.BaseViewModel
import com.rumbl.rumbl_pt.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 4/19/20.
 */


abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewDataBinding>(
    @LayoutRes layoutId: Int,
    clazz: KClass<ViewModel>,
    vararg viewModelParams: Any? = emptyArray()
) :
    Fragment(layoutId),
    IFragment {

    protected lateinit var binding: Binding

    protected val viewmodel: ViewModel by viewModel(clazz = clazz, parameters = {
        parametersOf(viewModelParams)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewmodel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        //TODO: these should be enhance or refactored for getting the localized string for common errors
        viewmodel.ioExceptionMessage = getString(R.string.txt_io_exception_message)
        viewmodel.socketTimeoutExceptionMessage =
            getString(R.string.txt_socket_timout_exception_message)
        viewmodel.internalServerErrorMessage = getString(R.string.txt_general_error)
        viewmodel.startLogic()
        onCreateInit(savedInstanceState)
    }

    fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
