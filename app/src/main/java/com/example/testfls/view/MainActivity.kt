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


    fun setFragment(fragment: Fragment, name: String) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            /*.addToBackStack(null)*/.commit()
    }

//    override fun onBackPressed() {
//        when {
//            supportFragmentManager.backStackEntryCount > 1 -> supportFragmentManager.popBackStack()
//            else -> finish()
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true

    }
}
