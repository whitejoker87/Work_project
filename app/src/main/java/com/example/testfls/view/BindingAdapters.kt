package com.example.testfls.view

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, visibility: Boolean) {
        if (visibility) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }

}