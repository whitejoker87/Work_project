package com.example.testfls.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testfls.R
import com.example.testfls.databinding.FragmentNewsitemListBinding
import com.example.testfls.di.utils.injectViewModel
import com.example.testfls.viewmodel.NewsListViewModel
import com.example.testfls.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class NewsListFragment : DaggerFragment(),
    SwipeRefreshLayout.OnRefreshListener,
    NewsItemRecyclerViewAdapter.NewsItemRecyclerViewAdapterCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsListViewModel>
    lateinit var newsListViewModel: NewsListViewModel

    lateinit var binding :FragmentNewsitemListBinding


    private val newsAdapter = NewsItemRecyclerViewAdapter(this)
    private var listenerListItemClickIn: OnListItemClickInFragmentListener? = null


    private fun loadData(pullToRefresh: Boolean) {
        newsListViewModel.setRefresh(pullToRefresh)
        newsListViewModel.getRss()
    }

    override fun onRefresh() {
        loadData(true)
    }

    override fun onItemClick(title: String) {
        listenerListItemClickIn?.onListItemClick(NewsDescriptionFragment.newInstance(title), FRAGMENT_ITEM)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsListViewModel = injectViewModel(viewModelFactory)

//        val onBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                requireActivity().finish()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onAttach(context: Context) {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newsitem_list, container, false)

        // Set the adapter
        with(binding) {
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = newsAdapter
            viewModel = newsListViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contentView.setOnRefreshListener(this)
        binding.lifecycleOwner = this

        loadData(false)

        newsListViewModel.getListNews().observe(viewLifecycleOwner, Observer {
                if (it != null){
                    newsAdapter.setNewsItems(it)
                    newsListViewModel.setRefresh(false)
                }
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
        (activity as MainActivity).supportActionBar?.title = resources.getText(R.string.news_list_title)
        setHasOptionsMenu(true)
    }

    interface OnListItemClickInFragmentListener {
        fun onListItemClick(fragment: Fragment, type: String)
    }
}
