package br.com.db1.bitcoinchartapp.extensions

import android.text.format.DateFormat
import java.util.*

fun convertTimestampToDate(time: Long): String {
    val cal = Calendar.getInstance(Locale("pt", "BR"))
    cal.timeInMillis = time * 1000
    return DateFormat.format("dd/MM", cal).toString()
}