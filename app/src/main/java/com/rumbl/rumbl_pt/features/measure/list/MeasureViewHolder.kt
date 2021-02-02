package com.rumbl.rumbl_pt.features.measure.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ruler.view.*

class MeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Int) {
        with(itemView)
        {
            tv_unit_not_selected.text = item.toString()
            iv_selector_indicator.visibility = View.INVISIBLE
        }
    }
}