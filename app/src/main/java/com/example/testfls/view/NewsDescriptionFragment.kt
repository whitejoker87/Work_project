package com.example.testfls.view

import android.os.Bundle
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testfls.R
import com.example.testfls.model.NewsItem
import com.example.testfls.presenter.NewsDescriptionPresenter
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState
import kotlinx.android.synthetic.main.fragment_news_description.*


private const val ARG_PARAM1 = "id"


class NewsDescriptionFragment : NewsDescriptionView, MvpLceViewStateFragment<SwipeRefreshLayout, NewsItem, NewsDescriptionView, NewsDescriptionPresenter>() {


    private var id: Int? = null

    override fun setData(data: NewsItem?) {
        title.text = data!!.title
        author.text = data.author
        date.text = data.pubDate
        description.text = data.description
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.getNewsItem(pullToRefresh, id!!)
    }

    override fun createPresenter(): NewsDescriptionPresenter {
        return NewsDescriptionPresenter()
    }

    override fun createViewState(): LceViewState<NewsItem, NewsDescriptionView> {
        return RetainingLceViewState()
    }

    override fun getData(): NewsItem {
       return NewsItem(title.text.toString(), author.text.toString(), date.text.toString(), description.text.toString())
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return "Description error message $e"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_description, container, false)
    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.title = "Description"

    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            NewsDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                }
            }
    }
}
