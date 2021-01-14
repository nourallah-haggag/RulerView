package com.rumbl.rumbl_pt.features.schedule

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder

class DayViewAdapter(
    private val DayItemInteractionListener: DayItemInteractionListener,
    private val lastSelectedDay: CalendarDay?
) : DayBinder<DayViewContainer> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(container: DayViewContainer, day: CalendarDay) {
        container.bindView(day, DayItemInteractionListener, lastSelectedDay)
    }

    override fun create(view: View): DayViewContainer = DayViewContainer(view)

}


interface DayItemInteractionListener {
    fun onDaySelected(selectedDay: CalendarDay)
}