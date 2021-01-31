package com.rumbl.rumbl_pt.utils

import com.rumbl.rumbl_pt.network.Constants.ARABIC_LOCALE
import com.rumbl.rumbl_pt.network.Constants.ENGLISH_LOCALE
import java.text.SimpleDateFormat
import java.util.*

object Dateutils {
    private const val DATE_FORMAT_DD_MM_YYYY = "yyyy-MM-dd"
    private const val DATE_FORMAT_SERVER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
    private const val DATE_FORMAT_HOURS_MINUTES_SECONDS = "HH:mm:ss"
    private const val LONG_DATE_FORMAT = "EEEE dd MMMM yyyy"
    private const val DATE_PATTERN_MINUTES_SECONDS = "mm:ss"

    fun formatToLongDate(stringDate: String): String? {
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY)
        val longDateFormat: SimpleDateFormat = if (Locale.getDefault().language == ARABIC_LOCALE) {
            SimpleDateFormat(LONG_DATE_FORMAT, Locale(ARABIC_LOCALE))
        } else {
            SimpleDateFormat(LONG_DATE_FORMAT, Locale(ENGLISH_LOCALE))
        }
        val date = simpleDateFormat.parse(stringDate)
        return longDateFormat.format(date!!)

    }

    fun formatDateToTimeStamp(stringDate: String): Long {
        val date = stringDate.substringBefore(".")
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT_SERVER_PATTERN)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return simpleDateFormat.parse(date).time
    }

    fun timeStampToMinutesSesconds(timeStamp: Long): String {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT_HOURS_MINUTES_SECONDS)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = Date(timeStamp)
        return simpleDateFormat.format(date)
    }


}