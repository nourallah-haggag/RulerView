package com.rumbl.rumbl_pt.features.home.home_list.latest_requests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.features.home.home_list.HomeItemsInteractionListener
import com.rumbl.rumbl_pt.features.home.home_list.SessionViewHolder
import com.rumbl.rumbl_pt.network.response.SessionsResponse

class LatestRequestsAdapter(
    private val sessions: List<SessionsResponse>,
    private val homeItemsInteractionListener: HomeItemsInteractionListener
) :
    RecyclerView.Adapter<SessionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SessionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_session, parent, false)
        return SessionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bind(sessions[position], homeItemsInteractionListener)
    }

    override fun getItemCount(): Int = sessions.size
}