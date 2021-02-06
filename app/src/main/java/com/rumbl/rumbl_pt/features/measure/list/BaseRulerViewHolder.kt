package com.rumbl.rumbl_pt.features.measure.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRulerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: SimpleRulerItem)
}