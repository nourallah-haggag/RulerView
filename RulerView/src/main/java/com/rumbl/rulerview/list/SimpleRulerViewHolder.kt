package com.rumbl.rulerview.list

import android.view.View
import androidx.core.content.ContextCompat
import com.rumbl.rulerview.R

class SimpleRulerViewHolder(itemView: View) : BaseRulerViewHolder(itemView) {
    override fun bind(item: SimpleRulerItem) {
        with(itemView) {
            if (item.isSelected) {
                findViewById<View>(R.id.view_center_dash).background =
                    ContextCompat.getDrawable(context, R.color.gc_red)
            } else {
                findViewById<View>(R.id.view_center_dash).background =
                    ContextCompat.getDrawable(context, R.color.cool_grey)
            }
        }
    }
}