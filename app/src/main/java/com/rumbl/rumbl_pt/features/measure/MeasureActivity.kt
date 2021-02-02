package com.rumbl.rumbl_pt.features.measure

import android.os.Bundle
import android.view.View
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.activities.BaseActivity
import com.rumbl.rumbl_pt.databinding.ActivityMeasureBinding
import com.rumbl.rumbl_pt.features.measure.list.MeasureAdapter
import kotlinx.android.synthetic.main.activity_measure.*
import kotlinx.android.synthetic.main.item_ruler.view.*


class MeasureActivity :
    BaseActivity<MeasureActivityViewModel, ActivityMeasureBinding>(MeasureActivityViewModel::class),
    MeasureAdapter.RulerInteractionListener {
    override val layoutId: Int = R.layout.activity_measure
    override fun onCreateInit(savedInstance: Bundle?) {
        val rulerAdapter = MeasureAdapter(
            min = 3,
            max = 200,
            context = this,
            rulerInteractionListener = this
        )
        rv_ruler.adapter = rulerAdapter
    }

    override fun onItemInFocus(value: Int) {
        ruler_selected.apply {

            view_center_indicator.visibility = View.INVISIBLE
            tv_unit.apply {
                text = value.toString()
                visibility = View.VISIBLE
            }
            tv_unit_label.apply {
                text = "cm"
                visibility = View.VISIBLE
            }
            view_outer_end_indicator.visibility = View.INVISIBLE
            view_outer_start_indicator.visibility = View.INVISIBLE
            view_inner_start_indicator.visibility = View.INVISIBLE
            view_inner_end_indicator.visibility = View.INVISIBLE
            view_inner_end_selected_indicator.visibility = View.VISIBLE
            view_inner_start_selected_indicator.visibility = View.VISIBLE
            view_end_filler.visibility = View.VISIBLE
            view_start_filler.visibility = View.VISIBLE

        }
    }

}