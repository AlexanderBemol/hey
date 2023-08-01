package com.amontdevs.hey.utils

import com.amontdevs.hey.model.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toTimestampFilename(username: String) =
    username + "-" + this.time.toString() + ".JPEG"

fun Date.formattedDate(): String =
    SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).format(this)


fun Date.daysTo(date: Date) : Int{
    val c1 = Calendar.getInstance()
    c1.time = this
    val d1 = c1.get(Calendar.DAY_OF_YEAR)
    val c2 = Calendar.getInstance()
    c2.time = date
    val d2 = c2.get(Calendar.DAY_OF_YEAR)

    return if(d1<=d2) d2 - d1
        else (365 - d1) + d2
}