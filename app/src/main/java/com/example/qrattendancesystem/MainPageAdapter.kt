package com.example.qrattendancesystem

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.qrattendancesystem.scanned_history.ScannedHistoryFragment
import com.example.qrattendancesystem.scanner.QRScannerFragment
import com.example.qrattendancesystem.user.UserFragment

class MainPageAdapter (var fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        // for index 0 scanner fragment
        // for index 1 history fragment
        // for index 3 user fragment
        return when (position) {
            0 -> QRScannerFragment.newInstance()
            1 -> ScannedHistoryFragment.newInstance()
            2 -> UserFragment.newInstance()
            else -> QRScannerFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return  3
    }
}