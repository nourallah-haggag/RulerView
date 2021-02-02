package com.rumbl.rumbl_pt.features.measure.list

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rumbl_pt.R

class MeasureAdapter(
    private val min: Int,
    private val max: Int,
    private val context: Context,
    private val rulerInteractionListener: RulerInteractionListener
) :
    RecyclerView.Adapter<MeasureViewHolder>() {
    private val units = mutableListOf<Int>().apply {
        for (i in min..max) {
            this.add(i)
        }
    }

    //    private var selectedPos: Int? = null
    private val pagerSnapHelper = LinearSnapHelper()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ruler, parent, false)
        return MeasureViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeasureViewHolder, position: Int) {
        holder.bind(units[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        pagerSnapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                pagerSnapHelper.findSnapView(recyclerView.layoutManager)?.let {
                    recyclerView.layoutManager?.getPosition(it)?.let { snappedViewPosition ->
                        rulerInteractionListener.onItemInFocus(units[snappedViewPosition])
                    }
                }
            }

//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    pagerSnapHelper.findSnapView(recyclerView.layoutManager)?.let {
//                        recyclerView.layoutManager?.getPosition(it)?.let { snappedViewPosition ->
//                            setItemSelected(snappedViewPosition)
//                        }
//
//                    }
//                }
//            }
        })
        recyclerView.setPadding(calculateRulerPadding(), 0, calculateRulerPadding(), 0)
        recyclerView.smoothScrollToPosition(units.size / 2)
    }


//    fun setItemSelected(position: Int) {
//        units[position].isSelected = true
//        notifyItemChanged(position)
//        selectedPos?.let {
//            if (position != it) {
//                units[it].isSelected = false
//                notifyItemChanged(it)
//            }
//        }
//        selectedPos = position
//    }

    private fun convertDpToPx(dp: Float): Int {
        val r: Resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            r.displayMetrics
        ).toInt()
    }

    private fun calculateRulerPadding(): Int {
        val width = context.resources.displayMetrics.widthPixels
        return width / 2 - convertDpToPx(50f)
    }

    override fun getItemCount(): Int = units.size

    interface RulerInteractionListener {
        fun onItemInFocus(value: Int)
    }


}