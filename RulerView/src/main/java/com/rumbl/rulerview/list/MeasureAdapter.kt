package com.rumbl.rulerview.list

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.rumbl.rulerview.R

class MeasureAdapter(
    private val min: Int,
    private val max: Int,
    private val context: Context,
    private val rulerMode: RulerMode = RulerMode.NORMAL,
    private val rulerInteractionListener: RulerInteractionListener
) :
    RecyclerView.Adapter<BaseRulerViewHolder>() {

    enum class RulerMode {
        NORMAL, SIMPLE
    }

    private val units = mutableListOf<SimpleRulerItem>().apply {
        for (i in min..max) {
            this.add(SimpleRulerItem(value = i))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (rulerMode) {
            RulerMode.NORMAL -> {
                R.layout.item_ruler
            }
            RulerMode.SIMPLE -> {
                R.layout.item_simple_ruler_horizontal
            }
        }
    }

    private var selectedPos: Int? = null
    private val pagerSnapHelper = LinearSnapHelper()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRulerViewHolder {
        return when (rulerMode) {
            RulerMode.NORMAL -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_ruler, parent, false)
                MeasureViewHolder(view)
            }
            RulerMode.SIMPLE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_simple_ruler_horizontal, parent, false)
                SimpleRulerViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: BaseRulerViewHolder, position: Int) {
        holder.bind(units[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        pagerSnapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                when (rulerMode) {
                    RulerMode.NORMAL -> {
                        pagerSnapHelper.findSnapView(recyclerView.layoutManager)?.let {
                            recyclerView.layoutManager?.getPosition(it)
                                ?.let { snappedViewPosition ->
                                    rulerInteractionListener.onNormalItemInFocus(units[snappedViewPosition])
                                }
                        }
                    }
                    RulerMode.SIMPLE -> {
                        pagerSnapHelper.findSnapView(recyclerView.layoutManager)?.let {
                            recyclerView.layoutManager?.getPosition(it)
                                ?.let { snappedViewPosition ->
                                    rulerInteractionListener.onSimpleItemInFocus(units[snappedViewPosition])
                                }
                        }
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (rulerMode == RulerMode.SIMPLE) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        pagerSnapHelper.findSnapView(recyclerView.layoutManager)?.let {
                            recyclerView.layoutManager?.getPosition(it)
                                ?.let { snappedViewPosition ->
                                    setItemSelected(snappedViewPosition)
                                }
                        }
                    }
                }
            }
        })
        recyclerView.setPadding(calculateRulerPadding(), 0, calculateRulerPadding(), 0)
        recyclerView.smoothScrollToPosition(units.size / 2)
        if (rulerMode == RulerMode.SIMPLE) {
            setItemSelected(units.size / 2)
        }
    }


    fun setItemSelected(position: Int) {
        units[position].isSelected = true
        notifyItemChanged(position)
        selectedPos?.let {
            if (position != it) {
                units[it].isSelected = false
                notifyItemChanged(it)
            }
        }
        selectedPos = position
    }

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
        fun onNormalItemInFocus(value: SimpleRulerItem)
        fun onSimpleItemInFocus(value: SimpleRulerItem)
    }


}