package com.rumbl.rumbl_pt.features.schedule

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.utils.Size
import com.kizitonwose.calendarview.utils.yearMonth
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentScheduleBinding
import com.rumbl.rumbl_pt.features.home.home_list.latest_requests.LatestRequestsAdapter
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.layout_loading.view.*
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class ScheduleFragment : BaseFragment<ScheculeViewModel, FragmentScheduleBinding>(
    layoutId = R.layout.fragment_schedule,
    clazz = ScheculeViewModel::class
), dayItemIteractor {
    private var lastSelectedDay: CalendarDay? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateInit(savedInstanceState: Bundle?) {
        val currentMonth = YearMonth.now()
        val lastMonth = currentMonth.plusMonths(3)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        binding.apply {
            calendarSesssions.setup(currentMonth, lastMonth, firstDayOfWeek)
            calendarSesssions.scrollToMonth(currentMonth)
            calendarSesssions.daySize = Size(350, 450)
            calendarSesssions.dayBinder = DayViewAdapter(this@ScheduleFragment, lastSelectedDay)
        }
        observeSessionsLiveEvent()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDaySelected(selectedDay: CalendarDay) {
        lastSelectedDay = selectedDay
        calendar_sesssions.dayBinder = DayViewAdapter(this@ScheduleFragment, lastSelectedDay)
        selectedDay.date.apply {
            dayOfMonth
            yearMonth
            year
            viewmodel.fetchSessionsByDate("$year-$monthValue-$dayOfMonth")
        }
    }

    private fun observeSessionsLiveEvent() {
        viewmodel.observeSessions().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    it.fetchData()?.let { sessions ->
                        if (sessions.isEmpty()) {
                            binding.lottieNoResultsAnim.visibility = View.VISIBLE
                            binding.lottieLoadingAnim.visibility = View.GONE
                            binding.rvSessions.visibility = View.GONE

                        } else {
                            binding.apply {
                                lottieLoadingAnim.visibility = View.GONE
                                rvSessions.visibility = View.VISIBLE
                            }
                            val adapter = LatestRequestsAdapter(sessions)
                            binding.rvSessions.adapter = adapter
                        }
                    }
                }
                CommonStatusImp.LOADING -> {
                    binding.apply {
                        lottieLoadingAnim.visibility = View.VISIBLE
                        lottieNoResultsAnim.visibility = View.GONE
                        rvSessions.visibility = View.GONE
                    }
                }
                CommonStatusImp.ERROR -> {
                    binding.apply {
                        lottieLoadingAnim.visibility = View.GONE
                        rvSessions.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}