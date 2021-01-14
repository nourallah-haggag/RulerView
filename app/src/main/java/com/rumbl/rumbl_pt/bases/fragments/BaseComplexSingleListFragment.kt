package com.rumbl.rumbl_pt.bases.fragments

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.bases.viewmodel.BaseSingleListViewModel
import kotlin.reflect.KClass

/**
 * Created by Mohamed Shalan on 11/11/20
 */

abstract class BaseComplexSingleListFragment<T, ViewModel : BaseSingleListViewModel<T>, DataBinding : ViewDataBinding>(
    @LayoutRes layout: Int,
    clazz: KClass<ViewModel>,
    vararg params: Any = emptyArray()
)
