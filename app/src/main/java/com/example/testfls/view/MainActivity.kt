package com.example.testfls.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.testfls.R
import com.example.testfls.di.utils.injectViewModel
import com.example.testfls.viewmodel.MainViewModel
import com.example.testfls.viewmodel.ViewModelFactory
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

const val FRAGMENT_LIST = "list"
const val FRAGMENT_ITEM = "newsItem"


class MainActivity : DaggerAppCompatActivity(), NewsListFragment.OnListItemClickInFragmentListener,
    HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) setFragment(NewsListFragment(), FRAGMENT_LIST)

        mainViewModel = injectViewModel(viewModelFactory)
    }


    private fun setFragment(fragment: Fragment, name: String) {
        val transaction = supportFragmentManager.beginTransaction()
        var fragmentLast: Fragment? = null

        if (supportFragmentManager.fragments.isNotEmpty()) {
            fragmentLast = supportFragmentManager.fragments.last()
        }

        if (fragmentLast == null) {
            transaction.add(R.id.fragment_container, fragment, name)
                .addToBackStack(null)
                .commit()
        } else {
            transaction
                .replace(R.id.fragment_container, fragment, name)
                .addToBackStack(null)
                .commit()
        }
    }


    override fun onBackPressed() {
        when {
            supportFragmentManager.fragments.last().javaClass == supportFragmentManager.findFragmentByTag(
                FRAGMENT_ITEM
            )?.javaClass -> setFragment(NewsListFragment(), FRAGMENT_LIST)

            else -> finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true

    }

    override fun onListItemClick(fragment: Fragment, type: String) {
        setFragment(fragment, type)
    }
}


