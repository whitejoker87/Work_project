package com.example.testfls.view

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, visibility: Boolean) {
        if (visibility) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:textFromMills")
    fun setTextFromMills(textView: TextView, mills: Long) {
        val millsToStringPattern = "EEE, dd LLL yyyy HH:mm:ss"
        val formatDate = SimpleDateFormat(millsToStringPattern, Locale.getDefault())
        textView.text = formatDate.format(Date(mills))

    }


}