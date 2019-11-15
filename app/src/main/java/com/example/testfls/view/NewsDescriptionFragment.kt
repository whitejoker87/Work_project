package com.example.testfls.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testfls.R
import com.example.testfls.di.utils.injectViewModel
//import com.example.testfls.viewmodel.MainViewModel
import com.example.testfls.viewmodel.NewsDescriptionViewModel
import com.example.testfls.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_description.*
import javax.inject.Inject
import javax.inject.Qualifier


const val ARG_TITLE = "title"


class NewsDescriptionFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var newsDescriptionViewModel: NewsDescriptionViewModel
//    lateinit var mainViewModel: MainViewModel


    private fun loadData() {
        newsDescriptionViewModel.sendNewsItem()
    }


//    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String =
//        resources.getText(R.string.description_error_message).toString() + " $e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDescriptionViewModel = injectViewModel(viewModelFactory)
    }

//    override fun onAttach(context: Context) {
////        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            title = it.getString(ARG_TITLE)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()

        newsDescriptionViewModel.getNewsItem().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                //         val webSet = full_new.settings
//        webSet.javaScriptEnabled = true
//        webSet.allowFileAccess =true
//        webSet.loadsImagesAutomatically = true
                full_new.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
                full_new.webViewClient = WebViewClient()
                full_new.loadUrl(it.link)

                toolbar.title = it.title
            }
        })
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
                    putString(ARG_TITLE, title)
                }
            }
    }
}
