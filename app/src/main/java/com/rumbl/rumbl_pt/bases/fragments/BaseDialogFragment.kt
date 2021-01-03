package com.rumbl.rumbl_pt.bases.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rumbl.rumbl_pt.R

/**
 * Created by Mohamed Shalan on 6/23/20.
 */


abstract class BaseDialogFragment<Binding : ViewDataBinding>(@LayoutRes private val layout: Int) :
	AppCompatDialogFragment(), IDialogFragment {

	protected lateinit var binding: Binding

	override fun getTheme(): Int = R.style.AppTheme_AlertDialog_NoTitle

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = DataBindingUtil.inflate(inflater, layout, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.lifecycleOwner = this
		onCreateInit(savedInstanceState)
	}
}
