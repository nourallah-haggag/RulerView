package com.rumbl.rumbl_pt.features.schedule

import android.view.View
import androidx.core.content.ContextCompat
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.ViewContainer
import com.rumbl.rumbl_pt.R
import kotlinx.android.synthetic.main.calendar_day_resource.view.*

class DayViewContainer(view: View) : ViewContainer(view) {
    fun bindView(
        day: CalendarDay,
        dayItemInteractionListener: DayItemInteractionListener,
        lastSelectedDay: CalendarDay?
    ) {
        with(view)
        {
            calendarDayText.text = day.date.dayOfMonth.toString()
            tv_day.text = day.date.dayOfWeek.toString()
            tv_month.text = day.date.month.toString()
            tv_year.text = day.date.year.toString()
            lastSelectedDay?.let { lastSelectedDay ->
                if (lastSelectedDay.date.dayOfYear == day.date.dayOfYear) {
                    calendarDayText.setTextColor(ContextCompat.getColor(context, R.color.white))
                    divider.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                    tv_day.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tv_month.setTextColor(ContextCompat.getColor(context, R.color.white))
                    tv_year.setTextColor(ContextCompat.getColor(context, R.color.white))
                    card_day_view.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.gc_red
                        )
                    )
                    card_day_view.strokeColor = ContextCompat.getColor(context, R.color.gc_red)
                } else {
                    calendarDayText.setTextColor(ContextCompat.getColor(context, R.color.warm_grey))
                    divider.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.cool_grey
                        )
                    )
                    tv_day.setTextColor(ContextCompat.getColor(context, R.color.warm_grey))
                    card_day_view.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.white
                        )
                    )
                    card_day_view.strokeColor = ContextCompat.getColor(context, R.color.cool_grey)
                    tv_month.setTextColor(ContextCompat.getColor(context, R.color.warm_grey))
                    tv_year.setTextColor(ContextCompat.getColor(context, R.color.warm_grey))
                }
            }
            view.setOnClickListener {
                dayItemInteractionListener.onDaySelected(day)
            }

        }

    }
}