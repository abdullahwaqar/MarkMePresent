package com.example.qrattendancesystem.dialogs

import android.app.Dialog
import android.content.Context
import com.example.qrattendancesystem.R
import kotlinx.android.synthetic.main.qr_result_layout.*

class QrCodeResultDialog(var context: Context) {

    private lateinit var dialog: Dialog
    private var qrResult: DialogResult? = null

    init {
        initDialog()
    }

    private fun initDialog() {
        dialog = Dialog(context)
        dialog.setContentView(R.layout.qr_result_layout)
        dialog.setCancelable(false)

        onClicks() //Add on click listeners
    }

    fun show(qrResult: DialogResult) {
        this.qrResult = qrResult
        dialog.scannedId.text = qrResult._id
        dialog.scannedDate.text = qrResult.class_date_time
        dialog.scannedText.text = "${qrResult.class_name} Class, Started By Teacher: ${qrResult.teacher_name}"
        dialog.show()
    }

    private fun onClicks() {
        dialog.markPresent.setOnClickListener {}

        dialog.cancelDialog.setOnClickListener {
            dialog.dismiss()
        }
    }
}