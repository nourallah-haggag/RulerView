package com.rumbl.rumbl_pt.features.schedule

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder

class DayViewAdapter(
    private val dayItemIteractor: dayItemIteractor,
    private val lastSelectedDay: CalendarDay?
) : DayBinder<DayViewContainer> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(container: DayViewContainer, day: CalendarDay) {
        container.bindView(day, dayItemIteractor, lastSelectedDay)
    }

    override fun create(view: View): DayViewContainer = DayViewContainer(view)

}


interface dayItemIteractor {
    fun onDaySelected(selectedDay: CalendarDay)
}