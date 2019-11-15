package com.example.testfls.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testfls.R
import com.example.testfls.di.utils.injectViewModel
import com.example.testfls.model.NewsItem
//import com.example.testfls.viewmodel.MainViewModel
import com.example.testfls.viewmodel.NewsListViewModel
import com.example.testfls.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_newsitem_list.*
import kotlinx.android.synthetic.main.fragment_newsitem_list.view.*
import javax.inject.Inject


class NewsListFragment : DaggerFragment(),
    SwipeRefreshLayout.OnRefreshListener,
    NewsItemRecyclerViewAdapter.NewsItemRecyclerViewAdapterCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var newsListViewModel: NewsListViewModel
//    lateinit var mainViewModel: MainViewModel


    private val newsAdapter = NewsItemRecyclerViewAdapter(this)
    private var listenerListItemClickIn: OnListItemClickInFragmentListener? = null

    private val itemTag = "newsItem"


    private fun loadData(pullToRefresh: Boolean) {
        newsListViewModel.setRefresh(pullToRefresh)
        newsListViewModel.getRss()
    }

//    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
//        contentView.isRefreshing = false
//        return resources.getText(R.string.list_news_error_message).toString() + " $e"
//    }

    override fun onRefresh() {
        loadData(true)
    }

    override fun onItemClick(title: String) {
        listenerListItemClickIn?.onListItemClick(NewsDescriptionFragment.newInstance(title), itemTag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsListViewModel = injectViewModel(viewModelFactory)
    }

    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        if (context is OnListItemClickInFragmentListener) {
            listenerListItemClickIn = context
        } else {
            throw RuntimeException("$context" + resources.getText(R.string.eroor_implements_listener).toString())
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerListItemClickIn = null
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

        newsListViewModel.getListNews().observe(viewLifecycleOwner, Observer {
            newsAdapter.setNewsItems(it)
            contentView.isRefreshing = false
            newsListViewModel.setRefresh(false)
        })

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
        toolbar.title = resources.getText(R.string.news_list_title)
        setHasOptionsMenu(true)

    }
    interface OnListItemClickInFragmentListener {
        fun onListItemClick(fragment: Fragment, name: String)
    }
}
