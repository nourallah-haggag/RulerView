package com.rumbl.rulerview.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.rumbl.rulerview.R

class MeasureViewHolder(itemView: View) : BaseRulerViewHolder(itemView) {
    override fun bind(item: SimpleRulerItem) {
        with(itemView)
        {
            findViewById<TextView>(R.id.tv_unit_not_selected).text = item.value.toString()
            findViewById<ImageView>(R.id.iv_selector_indicator).visibility = View.INVISIBLE
        }
    }
}