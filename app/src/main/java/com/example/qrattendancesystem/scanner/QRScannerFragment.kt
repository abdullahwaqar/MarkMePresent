package com.example.qrattendancesystem.scanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.dialogs.DialogResult
import com.example.qrattendancesystem.dialogs.QrCodeResultDialog
import com.google.gson.GsonBuilder
import com.google.zxing.Result
import kotlinx.android.synthetic.main.fragment_qrscanner.view.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QRScannerFragment : Fragment(), ZXingScannerView.ResultHandler {

    private lateinit var renderView: View
    private lateinit var scannerView: ZXingScannerView
    private lateinit var resultDialog: QrCodeResultDialog

    companion object {
        fun newInstance(): QRScannerFragment {
            return QRScannerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        renderView = inflater.inflate(R.layout.fragment_qrscanner, container, false)
        initializeViews()
        return renderView.rootView
    }

    private fun initializeViews() {
        initializeQRScanner()
        setResultDialog()
    }

    private fun setResultDialog() {
        resultDialog = QrCodeResultDialog(context!!)
    }

    private fun initializeQRScanner() {
        scannerView = ZXingScannerView(context!!) // Initialize the scanner view
        scannerView.setResultHandler(this) // Tell scanner we handle the result in this class
        // Set the configs
        scannerView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorTranslucent))
        scannerView.setBorderColor(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
        scannerView.setLaserColor(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
        scannerView.setBorderStrokeWidth(10)
        scannerView.setAutoFocus(true)
        scannerView.setSquareViewFinder(true)

        startQrCamera() // Start Qr Camera
        renderView.containerScanner.addView(scannerView) // Add the scanner to the main renderer
    }

    private fun startQrCamera() {
        scannerView.startCamera()
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this) // Register ourselves as a handler for scan results.
        scannerView.startCamera()          // Start camera on resume
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera() // On pause stop the camera
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera() // On pause stop the camera
    }

    override fun handleResult(rawResult: Result?) {
        onQrResult(rawResult?.text)
        scannerView.resumeCameraPreview(this)
    }

    private fun onQrResult(result: String?) {
        if (result.isNullOrEmpty()) {
            Toast.makeText(context!!, "Invalid Qr Code", Toast.LENGTH_SHORT).show()
        } else {
            println(result)
            val gson = GsonBuilder().create()
            val dialogResult = gson.fromJson(result, DialogResult::class.java)
            resultDialog.show(dialogResult)
        }
    }
}
