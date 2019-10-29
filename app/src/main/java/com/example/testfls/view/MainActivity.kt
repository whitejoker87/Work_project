package com.example.testfls.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testfls.R


class MainActivity : AppCompatActivity(), NewsListFragment.OnListItemClickInFragmentListener {


    private val listTag = "list"
    private val itemTag = "newsItem"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) setFragment(NewsListFragment(), listTag)
    }

    private fun showFragment(fragment: Fragment){
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.fragments.last()).show(fragment).commit()

    }



    private fun setFragment(fragment: Fragment, name: String) {
        val fragmentReady: Fragment? = supportFragmentManager.findFragmentByTag(name)
        if (fragmentReady != null && fragment.javaClass == fragmentReady.javaClass) showFragment(fragmentReady)
        else {
            val transaction = supportFragmentManager.beginTransaction()
            if (supportFragmentManager.fragments.isNotEmpty()) {
                transaction.hide(supportFragmentManager.fragments.last()).add(R.id.fragment_container, fragment, name).addToBackStack(null).commit()
            } else {
                transaction.add(R.id.fragment_container, fragment, name).addToBackStack(null).commit()
            }
        }
    }


    override fun onBackPressed() {
        when {
            supportFragmentManager.fragments.last().javaClass == supportFragmentManager.findFragmentByTag(itemTag)?.javaClass -> setFragment(NewsListFragment(), listTag)
//            supportFragmentManager.backStackEntryCount > 1 -> supportFragmentManager.popBackStack()
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
