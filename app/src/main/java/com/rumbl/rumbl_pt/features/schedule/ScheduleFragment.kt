package com.rumbl.rumbl_pt.features.schedule

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.utils.Size
import com.kizitonwose.calendarview.utils.yearMonth
import com.rumbl.rumbl_pt.R
import com.rumbl.rumbl_pt.bases.fragments.BaseFragment
import com.rumbl.rumbl_pt.bases.states.CommonStatusImp
import com.rumbl.rumbl_pt.databinding.FragmentScheduleBinding
import com.rumbl.rumbl_pt.features.home.home_list.HomeItemsInteractionListener
import com.rumbl.rumbl_pt.features.home.home_list.latest_requests.LatestRequestsAdapter
import com.rumbl.rumbl_pt.features.session_details.SessionDetailsFragment
import com.rumbl.rumbl_pt.network.response.SessionsResponse
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.layout_loading.view.*
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*


class ScheduleFragment : BaseFragment<ScheculeViewModel, FragmentScheduleBinding>(
    layoutId = R.layout.fragment_schedule,
    clazz = ScheculeViewModel::class
), DayItemInteractionListener, HomeItemsInteractionListener {

    private var lastSelectedDay: CalendarDay? = null
    private var loadingView: View? = null
    private var noResultsView: View? = null

    override fun onCreateInit(savedInstanceState: Bundle?) {
        val currentMonth = YearMonth.now()
        val lastMonth = currentMonth.plusMonths(3)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        binding.apply {
            calendarSesssions.setup(currentMonth, lastMonth, firstDayOfWeek)
            calendarSesssions.scrollToMonth(currentMonth)
            calendarSesssions.daySize = Size(convertDpToPx(100f), convertDpToPx(130f))
            calendarSesssions.dayBinder = DayViewAdapter(this@ScheduleFragment, lastSelectedDay)
        }
        observeSessionsLiveEvent()
    }

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

    private fun convertDpToPx(dp: Float): Int {
        val r: Resources = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            r.displayMetrics
        ).toInt()
    }

    private fun observeSessionsLiveEvent() {
        viewmodel.observeSessions().observe(viewLifecycleOwner, {
            when (it.whichStatus()) {
                CommonStatusImp.SUCCESS -> {
                    it.fetchData()?.let { sessions ->
                        if (sessions.isEmpty()) {
                            binding.apply {
                                if (noResultsView == null) {
                                    noResultsView = lottieNoResultsAnimStub.viewStub?.inflate()
                                } else {
                                    noResultsView?.visibility = View.VISIBLE
                                }
                                loadingView?.visibility = View.GONE
                                rvSessions.visibility = View.GONE
                            }

                        } else {
                            binding.apply {
                                loadingView?.visibility = View.GONE
                                rvSessions.visibility = View.VISIBLE
                            }
                            val adapter = LatestRequestsAdapter(sessions, this)
                            binding.rvSessions.adapter = adapter
                        }
                    }
                }
                CommonStatusImp.LOADING -> {
                    binding.apply {
                        if (loadingView == null) {
                            loadingView = lottieLoadingAnimStub.viewStub?.inflate()
                        } else {
                            loadingView?.visibility = View.VISIBLE
                        }
                        noResultsView?.visibility = View.GONE
                        rvSessions.visibility = View.GONE
                    }
                }
                CommonStatusImp.ERROR -> {
                    binding.apply {
                        loadingView?.visibility = View.GONE
                        rvSessions.visibility = View.VISIBLE
                    }
                    it.fetchError()?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onAllLatestSessionsClicked() {
    }

    override fun onAllUpcomingSessionsClicked() {
    }

    override fun onSessionItemClicked(session: SessionsResponse) {
        findNavController().navigate(
            R.id.action_schedule_to_session_details,
            SessionDetailsFragment.passSessionInfo(
                session,
                SessionDetailsFragment.SessionDetailsType.NORMAL_SESSION_DETAILS
            )
        )
    }

    override fun onAcceptSessionClicked() {
    }

    override fun onStart() {
        super.onStart()
        loadingView = null
        noResultsView = null
        lastSelectedDay?.let { lastSelectedDay ->
            binding.calendarSesssions.scrollToDay(lastSelectedDay)
        }
    }
}