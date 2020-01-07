package com.example.qrattendancesystem.scanner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qrattendancesystem.R
import com.google.zxing.integration.android.IntentIntegrator

class QRScannerFragment : Fragment() {

    private lateinit var renderView: View
    private lateinit var scannerView: IntentIntegrator

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
        renderView = inflater.inflate(R.layout.fragment_qrscanner, container, false)
        initializeQRScanner()
        return renderView.rootView
    }


    private fun initializeQRScanner() {
        scannerView = IntentIntegrator.forSupportFragment(this)
        scannerView?.setOrientationLocked(true)
        scannerView?.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        print(result)
        super.onActivityResult(requestCode, resultCode, data)
    }


}
