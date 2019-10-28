package com.example.testfls.view

import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
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
private const val ARG_PARAM2 = "title"


class NewsDescriptionFragment : NewsDescriptionView, MvpLceViewStateFragment<SwipeRefreshLayout, NewsItem, NewsDescriptionView, NewsDescriptionPresenter>() {


    private var id: Int? = 0
    private var title: String? = ""
    private lateinit var newsItem: NewsItem

    override fun setData(data: NewsItem?) {

        newsItem = data!!

         val webSet = full_new.settings
//        webSet.javaScriptEnabled = true
//        webSet.allowFileAccess =true
//        webSet.loadsImagesAutomatically = true
        full_new.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        full_new.webViewClient = WebViewClient()
        full_new.loadUrl(newsItem.link)

        toolbar.title = newsItem.title
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.getNewsItem(pullToRefresh, id!!, title!!)
    }

    override fun createPresenter(): NewsDescriptionPresenter {
        return NewsDescriptionPresenter()
    }

    override fun createViewState(): LceViewState<NewsItem, NewsDescriptionView> {
        return RetainingLceViewState()
    }

    override fun getData(): NewsItem {
       return NewsItem(newsItem.title, newsItem.author, newsItem.pubDate, newsItem.link)
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return "$resources.getText(R.string.description_error_message) $e"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
            title = it.getString(ARG_PARAM2)
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


    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, title: String) =
            NewsDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                    putString(ARG_PARAM2, title)
                }
            }
    }
}
