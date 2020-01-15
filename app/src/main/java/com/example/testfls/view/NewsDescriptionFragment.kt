package com.example.testfls.view

import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.testfls.R
import com.example.testfls.databinding.FragmentNewsDescriptionBinding
import com.example.testfls.di.utils.injectViewModel
import com.example.testfls.viewmodel.MainViewModel
import com.example.testfls.viewmodel.NewsDescriptionViewModel
import com.example.testfls.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


const val ARG_TITLE = "Title"


class NewsDescriptionFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsDescriptionViewModel>
    lateinit var newsDescriptionViewModel: NewsDescriptionViewModel
    lateinit var mainViewModel: MainViewModel

    lateinit var binding: FragmentNewsDescriptionBinding


    private fun loadData() {
        newsDescriptionViewModel.sendNewsItem()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = requireActivity().injectViewModel(viewModelFactory)
        newsDescriptionViewModel = injectViewModel(viewModelFactory)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_description, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        loadData()

        with(newsDescriptionViewModel) {

            getNewsItem().observe(viewLifecycleOwner, Observer {
                if (it != null) {
//                    val webSet = full_new.settings
//                    webSet.cacheMode = WebSettings.LOAD_NO_CACHE
//                    webSet.javaScriptEnabled = true
//                    webSet.allowFileAccess =true
//                    webSet.loadsImagesAutomatically = true
                    binding.fullNew.webChromeClient = WebChromeClient()
                    binding.fullNew.loadUrl(it.link)

                    (activity as MainActivity).supportActionBar?.title = it.title
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()


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
