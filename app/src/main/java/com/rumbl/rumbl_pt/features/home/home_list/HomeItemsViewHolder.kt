package com.rumbl.rumbl_pt.features.home.home_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class HomeItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun <T> bind(
        item: T,
        homeItemsInteractionListener: HomeItemsInteractionListener? = null
    )
}