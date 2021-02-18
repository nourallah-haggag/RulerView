package com.rumbl.rulerview.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRulerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: SimpleRulerItem)
}