package com.rumbl.rumbl_pt.features.home.home_list

import android.view.View
import com.rumbl.rumbl_pt.models.HeaderItem
import kotlinx.android.synthetic.main.item_header.view.*

class HomeHeaderItemViewHolder(itemView: View) : HomeItemsViewHolder(itemView) {
    override fun <T> bind(item: T, homeItemsInteractionListener: HomeItemsInteractionListener?) {
        if (item is HeaderItem) {
            with(itemView)
            {
                iv_open_all.setOnClickListener {
                    homeItemsInteractionListener?.onAllUpcomingSessionsClicked()
                }
            }
        }
    }
}