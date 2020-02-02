package com.example.app_25_weightapp

import android.app.DatePickerDialog
import android.widget.EditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

private val df = SimpleDateFormat("yyyy-MM-dd", Locale.US)

fun showDatePickerDialog(editText: EditText) {
    val listener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, monthOfYear)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
        editText.setText(cal.toFormattedString())
    }

    val cal = editText.text.toString().toCalendar()
    DatePickerDialog(
        editText.context,
        listener,
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH)
    ).show()
}

fun Calendar.toFormattedString(): String = df.format(time)

fun String.toCalendar(): Calendar = Calendar.getInstance().apply {
    time = toDateOrToday()
}

fun String.toDateOrToday(): Date = try {
    df.parse(this) ?: Date()
} catch (e: ParseException) {
    Date()
}

fun String.toFloatOrZero(): Float {
    return toFloatOrNull() ?: 0f
}

fun computeBmi(weight: String, height: String): String {
    val _weight = weight.toDouble()
    val _height = height.toDouble()
    return String.format(Locale.US, "%.1f", _weight / _height.pow(2))
}