package com.rumbl.rumbl_pt.features.home.home_list

import android.view.View
import com.rumbl.rumbl_pt.models.HeaderItem

class HomeHeaderItemViewHolder(itemView: View) : HomeItemsViewHolder(itemView) {
    override fun <T> bind(item: T, homeItemsInteractionListener: HomeItemsInteractionListener) {
        if (item is HeaderItem) {

        }
    }
}