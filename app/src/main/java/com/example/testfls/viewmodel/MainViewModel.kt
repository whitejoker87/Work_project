package com.example.testfls.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

const val FRAGMENT_LIST = "list"
const val FRAGMENT_ITEM = "newsItem"

class MainViewModel @Inject constructor() : ViewModel() {

    private val titleDescription = MutableLiveData<String>("")
    private val fragmentTypeForStart = MutableLiveData<String>("")

    fun setTitleDescription(title: String) {
        titleDescription.value = title
    }

    fun getTitleDescription(): LiveData<String> = titleDescription


    fun setFragmentTypeForStart(type: String) {
        fragmentTypeForStart.value = type
    }

    fun getFragmentTypeForStart(): LiveData<String> = fragmentTypeForStart


    fun startDescriptionFragment(title: String) {
        setTitleDescription(title)
        setFragmentTypeForStart(FRAGMENT_ITEM)
    }

    fun startListFragment() {
        setTitleDescription("")
        setFragmentTypeForStart(FRAGMENT_LIST)
    }
}