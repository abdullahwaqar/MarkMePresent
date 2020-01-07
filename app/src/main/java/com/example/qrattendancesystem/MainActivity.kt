package com.example.qrattendancesystem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> bottomNavigationView.selectedItemId = R.id.scanMenuId
                    1 -> bottomNavigationView.selectedItemId = R.id.historyMenuId
                    2 -> bottomNavigationView.selectedItemId = R.id.profileMenuId
                }
            }
        })
    }

    private fun setBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.scanMenuId -> viewPager.currentItem = 0
                R.id.historyMenuId -> viewPager.currentItem = 1
                R.id.profileMenuId -> viewPager.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setViewPagerAdapter() {
        viewPager.adapter = MainPageAdapter(supportFragmentManager)
    }
}
