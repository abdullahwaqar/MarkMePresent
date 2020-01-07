package com.example.qrattendancesystem.scanner


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.qrattendancesystem.R

class QRScannerFragment : Fragment() {

    companion object {
        fun newInstance() : QRScannerFragment {
            return QRScannerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrscanner, container, false)
    }


}
