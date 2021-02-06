package com.rumbl.rumbl_pt.features.measure.list

import android.view.View
import kotlinx.android.synthetic.main.item_ruler.view.*

class MeasureViewHolder(itemView: View) : BaseRulerViewHolder(itemView) {
    override fun bind(item: SimpleRulerItem) {
        with(itemView)
        {
            tv_unit_not_selected.text = item.value.toString()
            iv_selector_indicator.visibility = View.INVISIBLE
        }
    }
}