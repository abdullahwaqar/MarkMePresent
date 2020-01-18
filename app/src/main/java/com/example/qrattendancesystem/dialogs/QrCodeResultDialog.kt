package com.example.qrattendancesystem.dialogs

import android.app.Dialog
import android.content.Context
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.db.models.QrResult
import kotlinx.android.synthetic.main.qr_result_layout.*

class QrCodeResultDialog(var context: Context) {

    private lateinit var dialog: Dialog
    private var qrResult: QrResult? = null

    init {
        initDialog()
    }

    private fun initDialog() {
        dialog = Dialog(context)
        dialog.setContentView(R.layout.qr_result_layout)
        dialog.setCancelable(false)

        onClicks() //Add on click listeners
    }

    fun show(qrResult: QrResult) {
        this.qrResult = qrResult

    }

    private fun onClicks() {
        dialog.markPresent.setOnClickListener {}

        dialog.cancelDialog.setOnClickListener {}
    }
}