package com.example.testfls

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.testfls.dummy.DummyContent
import com.example.testfls.dummy.DummyContent.DummyItem
import com.example.testfls.model.NewsItem
import com.example.testfls.presenter.NewsPresenter
import com.example.testfls.view.NewsView
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState


class NewsListFragment : NewsView, MvpLceViewStateFragment<SwipeRefreshLayout, List<NewsItem>, NewsView, NewsPresenter>(), SwipeRefreshLayout.OnRefreshListener {

    val newsAdapter = NewsItemRecyclerViewAdapter(DummyContent.ITEMS)


    override fun createPresenter(): NewsPresenter {
        return NewsPresenter()
    }

    override fun createViewState(): LceViewState<List<NewsItem>, NewsView> {
        return RetainingLceViewState()
    }


    override fun setData(data: List<NewsItem>?) {
        newsAdapter.setNewsItems(listOf())
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadNews(pullToRefresh)
    }

    override fun getData(): List<NewsItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefresh() {
        loadData(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_newsitem_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = newsAdapter
            }
        }
        return view
    }

}
