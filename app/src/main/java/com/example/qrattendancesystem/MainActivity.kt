package com.example.qrattendancesystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set default pager adapter
        setViewPagerAdapter()
        // Change the selected item in the navigation below
        setBottomNavigation()
        // Set the pager listener to update navigation on swipe
        setViewPagerListener()
    }

    private fun setViewPagerListener() {
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                bottomNavigationView.selectedItemId = when (position) {
                    0 -> R.id.scanMenuId
                    1 -> R.id.historyMenuId
                    2 -> R.id.profileMenuId
                    else -> R.id.scanMenuId
                }
            }
        })
    }

    private fun setBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            viewPager.currentItem = when (it.itemId) {
                R.id.scanMenuId -> 0
                R.id.historyMenuId -> 1
                R.id.profileMenuId -> 2
                else -> 0
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setViewPagerAdapter() {
        viewPager.adapter = MainPageAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 2
    }
}
