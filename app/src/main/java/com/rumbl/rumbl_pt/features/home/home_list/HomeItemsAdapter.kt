package com.rumbl.rumbl_pt.features.home.home_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.models.HeaderItem
import com.rumbl.rumbl_pt.models.IHomeScreenModel
import com.rumbl.rumbl_pt.models.LatestSessionsRequests
import com.rumbl.rumbl_pt.network.response.SessionsResponse

class HomeItemsAdapter(
    private val homeItems: List<IHomeScreenModel>,
    private val homeItemInterActionListener: HomeItemsInteractionListener
) :
    RecyclerView.Adapter<HomeItemsViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (homeItems[position]) {
            is LatestSessionsRequests -> R.layout.item_latest_requests
            is HeaderItem -> R.layout.item_header
            is SessionsResponse -> R.layout.item_session
            else -> throw IllegalStateException("HomeListItems: no such item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemsViewHolder {
        return when (viewType) {
            R.layout.item_latest_requests -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_latest_requests, parent, false)
                LatestRequestsItemViewHolder(view)
            }
            R.layout.item_header -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                HomeHeaderItemViewHolder(view)
            }
            R.layout.item_session -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_session, parent, false)
                SessionViewHolder(view)
            }
            else -> throw IllegalStateException("HomeListItems: no such item type")
        }
    }

    override fun onBindViewHolder(holder: HomeItemsViewHolder, position: Int) {
        holder.bind(homeItems[position], homeItemInterActionListener)
    }

    override fun getItemCount(): Int = homeItems.size
}

interface HomeItemsInteractionListener {
    fun onAllLatestSessionsClicked()
    fun onAllUpcomingSessionsClicked()
    fun onSessionItemClicked(session:SessionsResponse)
    fun onAcceptSessionClicked()
}