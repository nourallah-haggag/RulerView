package com.rumbl.rumbl_pt.bases.fragments

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.MergeAdapter
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
) : BaseFragment<ViewModel, DataBinding>(layout, clazz, params), IComplexSingleListFragment<T> {


    protected var adapter: MergeAdapter? = null

    override fun onCreateInit(savedInstanceState: Bundle?) {
        if (getRecyclerView().layoutManager == null)
            throw RuntimeException("you have to set the layout manager")

        adapter = MergeAdapter()
        getAdapters().forEach { adapter?.addAdapter(it) }

        observeData()
        getRecyclerView().adapter = adapter

        getSwipeRefresh()?.setOnRefreshListener {
            viewmodel.refreshData = true
            viewmodel.startLogic()
            showLoading()
        }
    }

    open fun observeData() {
        viewmodel.data_.observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.LOADING -> {
                    disableSwipeToRefreshDuringLoading()
                    showLoading()
                }
                CommonStatusImp.SUCCESS -> {
                    enableSwipeToRefresh()
                    hideLoading()
                    showData(it.fetchData())
                }
                CommonStatusImp.ERROR -> {
                    enableSwipeToRefresh()
                    hideLoading()
                    showError(it.fetchError())
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getRecyclerView().adapter = null
        adapter = null
    }
}