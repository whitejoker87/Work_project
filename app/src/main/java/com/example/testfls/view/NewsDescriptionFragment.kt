package com.example.testfls.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testfls.R
import com.example.testfls.model.NewsItem
import com.example.testfls.presenter.NewsDescriptionPresenter
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_news_description.*
import javax.inject.Inject


private const val ARG_PARAM2 = "title"


class NewsDescriptionFragment : NewsDescriptionView, MvpLceViewStateFragment<SwipeRefreshLayout, NewsItem, NewsDescriptionView, NewsDescriptionPresenter>() {

    @Inject
    lateinit var newsDescriptionPresenter: NewsDescriptionPresenter

    override fun setData(data: NewsItem?) {

//         val webSet = full_new.settings
//        webSet.javaScriptEnabled = true
//        webSet.allowFileAccess =true
//        webSet.loadsImagesAutomatically = true
        full_new.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        full_new.webViewClient = WebViewClient()
        full_new.loadUrl(data?.link)

        toolbar.title = data?.title
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.getNewsItem(pullToRefresh)
    }

    override fun createPresenter(): NewsDescriptionPresenter =
        newsDescriptionPresenter!!

    override fun createViewState(): LceViewState<NewsItem, NewsDescriptionView> =
        RetainingLceViewState()

    override fun getData(): NewsItem =
        presenter.item

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String =
        resources.getText(R.string.description_error_message).toString() + " $e"

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            presenter.title = it.getString(ARG_PARAM2)
//        }
//    }

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
        fun newInstance(title: String) =
            NewsDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM2, title)
                }
            }
    }
}
