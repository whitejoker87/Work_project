package com.example.testfls.view

import com.example.testfls.model.NewsItem
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView

interface NewsView : MvpLceView<List<NewsItem>> {
}