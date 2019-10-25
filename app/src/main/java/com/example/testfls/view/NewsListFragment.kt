package com.example.testfls.view

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testfls.R

import com.example.testfls.model.NewsItem
import com.example.testfls.presenter.NewsPresenter
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState
import kotlinx.android.synthetic.main.fragment_newsitem_list.*
import kotlinx.android.synthetic.main.fragment_newsitem_list.view.*


class NewsListFragment : NewsView,
    MvpLceViewStateFragment<SwipeRefreshLayout, List<NewsItem>, NewsView, NewsPresenter>(),
    SwipeRefreshLayout.OnRefreshListener,
    NewsItemRecyclerViewAdapter.NewsItemRecyclerViewAdapterCallback {


    private val newsAdapter = NewsItemRecyclerViewAdapter(this)


    override fun onStart() {
        super.onStart()
    }

    override fun createPresenter(): NewsPresenter {
        return NewsPresenter()
    }


    override fun createViewState(): LceViewState<List<NewsItem>, NewsView> {
        return RetainingLceViewState()
    }

    override fun setData(data: List<NewsItem>?) {
        newsAdapter.setNewsItems(data!!)
        contentView.isRefreshing = false
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.getRss(pullToRefresh)
    }

    override fun getData(): List<NewsItem> {
        return newsAdapter.getNewsItems()
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        contentView.isRefreshing = false
        return "News list error message $e"
    }


    override fun onRefresh() {
        loadData(true)
    }

    override fun onItemClick(pos: Int) {
        (activity as MainActivity).setFragment(NewsDescriptionFragment.newInstance(pos), "newsItem")
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView.setOnRefreshListener(this)
        loadData(false)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_refresh) {
            loadData(true)
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onResume() {
        super.onResume()

        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.title = "Rss"
        setHasOptionsMenu(true)

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
