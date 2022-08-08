package com.zenjob.android.browsr.utils

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object AppUtils{
    @SuppressLint("SimpleDateFormat")
    fun getDate(dateStr: String): String {
        val sdf: DateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
        try {
            val date: Date = sdf.parse(dateStr) as Date
            val form=SimpleDateFormat("dd MMM yyyy")
            return form.format(date)
        }catch (e:ParseException){
            return ""
        }
    }
}

