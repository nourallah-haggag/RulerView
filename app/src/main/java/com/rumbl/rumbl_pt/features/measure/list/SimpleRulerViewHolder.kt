package com.rumbl.rumbl_pt.features.measure.list

import android.view.View
import androidx.core.content.ContextCompat
import com.rumbl.rumbl_pt.R
import kotlinx.android.synthetic.main.item_simple_ruler_horizontal.view.*

class SimpleRulerViewHolder(itemView: View) : BaseRulerViewHolder(itemView) {
    override fun bind(item: SimpleRulerItem) {
        with(itemView) {
            if (item.isSelected) {
                view_center_dash.background = ContextCompat.getDrawable(context, R.color.gc_red)
            } else {
                view_center_dash.background = ContextCompat.getDrawable(context, R.color.cool_grey)
            }
        }
    }
}