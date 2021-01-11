package com.rumbl.rumbl_pt.features.home.home_list

import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import com.rumbl.rumbl_pt.features.home.home_list.latest_requests.LatestRequestsAdapter
import com.rumbl.rumbl_pt.models.LatestSessionsRequests
import kotlinx.android.synthetic.main.item_latest_requests.view.*

class LatestRequestsItemViewHolder(itemView: View) : HomeItemsViewHolder(itemView) {
    override fun <T> bind(item: T, homeItemsInteractionListener: HomeItemsInteractionListener?) {
        if (item is LatestSessionsRequests) {
            with(itemView)
            {
                val sessionsAdapter =
                    LatestRequestsAdapter(item.sessions, homeItemsInteractionListener)
                rv_sessions_latest_requests.adapter = sessionsAdapter
                val pagerSnapHelper = PagerSnapHelper()
                pagerSnapHelper.attachToRecyclerView(rv_sessions_latest_requests)
            }
        }
    }
}