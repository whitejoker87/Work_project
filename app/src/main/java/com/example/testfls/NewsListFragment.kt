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

import com.example.testfls.model.NewsItem
import com.example.testfls.presenter.NewsPresenter
import com.example.testfls.view.NewsView
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState
import kotlinx.android.synthetic.main.fragment_newsitem_list.view.*


class NewsListFragment : NewsView,
    MvpLceViewStateFragment<SwipeRefreshLayout, List<NewsItem>, NewsView, NewsPresenter>(),
    SwipeRefreshLayout.OnRefreshListener, NewsItemRecyclerViewAdapter.NewsItemRecyclerViewAdapterCallback {

    override fun onItemClick(pos: Int) {
        (activity as MainActivity).setFragment(NewsItemFragment.newInstance(pos), "newsItem")
    }

    private val newsAdapter = NewsItemRecyclerViewAdapter(this)


    override fun createPresenter(): NewsPresenter {
        return NewsPresenter()
    }

    override fun createViewState(): LceViewState<List<NewsItem>, NewsView> {
        return RetainingLceViewState()
    }


    override fun setData(data: List<NewsItem>?) {
        newsAdapter.setNewsItems(data!!)
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.getRss(pullToRefresh)
    }

    override fun getData(): List<NewsItem> {
        return newsAdapter.getNewsItems()
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return "Dummy error message"
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
        with(view) {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = newsAdapter
        }


        return view
    }

}
