package com.rumbl.rumbl_pt.bases.fragments

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mohamed Shalan on 11/11/20
 */

interface IComplexSingleListFragment<T> : IListCommonData<T> {

    fun getAdapters(): List<RecyclerView.Adapter<*>>
}