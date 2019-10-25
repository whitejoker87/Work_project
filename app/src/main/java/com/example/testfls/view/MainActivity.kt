package com.example.testfls.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testfls.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(NewsListFragment(), "list")
    }

    private fun showFragment(fragment: Fragment){
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.fragments[0]).show(fragment)

    }



    fun setFragment(fragment: Fragment, name: String) {
        val fragmentReady = supportFragmentManager.findFragmentByTag(name)
        if (fragment == fragmentReady) showFragment(fragment)
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
            supportFragmentManager.fragments.last() == supportFragmentManager.findFragmentByTag("newsItem") -> setFragment(NewsListFragment(), "list")
//            supportFragmentManager.backStackEntryCount > 1 -> supportFragmentManager.popBackStack()
            else -> finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true

    }
}
