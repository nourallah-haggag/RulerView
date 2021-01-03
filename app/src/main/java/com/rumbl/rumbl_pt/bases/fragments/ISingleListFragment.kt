package com.rumbl.rumbl_pt.bases.fragments

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mohamed Shalan on 6/10/20.
 */

interface ISingleListFragment<T, Adapter : RecyclerView.Adapter<*>> : IListCommonData<T> {

    fun getAdapter(): Adapter

}
