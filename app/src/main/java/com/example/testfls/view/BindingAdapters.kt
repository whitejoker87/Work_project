package com.example.testfls.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    private const val millsToStringPattern = "EEE, dd LLL yyyy HH:mm:ss"
    @SuppressLint("ConstantLocale")
    private val formatDate = SimpleDateFormat(millsToStringPattern, Locale.getDefault())

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, visibility: Boolean) {
        if (visibility) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:textFromMills")
    fun setTextFromMills(textView: TextView, mills: Long) {
        textView.text = formatDate.format(Date(mills))

    }


}