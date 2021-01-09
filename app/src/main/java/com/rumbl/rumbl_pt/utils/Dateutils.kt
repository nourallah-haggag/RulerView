package com.rumbl.rumbl_pt.utils

import com.rumbl.rumbl_pt.network.Constants.ARABIC_LOCALE
import com.rumbl.rumbl_pt.network.Constants.ENGLISH_LOCALE
import java.text.SimpleDateFormat
import java.util.*

object Dateutils {
    private const val DATE_FORMAT_DD_MM_YYYY = "yyyy-MM-dd"
    private const val LONG_DATE_FORMAT = "EEEE dd MMMM yyyy"

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
}