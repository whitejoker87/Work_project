package com.example.testfls.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testfls.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), NewsListFragment.OnListItemClickInFragmentListener,
    HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private val listTag = "list"
    private val itemTag = "newsItem"


    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) setFragment(NewsListFragment(), listTag)
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
                itemTag
            )?.javaClass -> setFragment(NewsListFragment(), listTag)

            else -> finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true

    }

    override fun onListItemClick(fragment: Fragment, name: String) {
        setFragment(fragment, name)
    }
}
