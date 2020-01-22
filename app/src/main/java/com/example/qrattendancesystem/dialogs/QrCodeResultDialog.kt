package com.example.qrattendancesystem.dialogs

import android.app.Dialog
import android.content.Context
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.db.models.QrResult
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
        dialog.scannedText.text =
            "${qrResult.class_name} Class, Started By Teacher: ${qrResult.teacher_name}"
        dialog.show()
    }

    private fun onClicks() {
        dialog.markPresent.setOnClickListener {
            // Make a network request

            // Save the result in database
//            val writeResult: QrResult = QrResult(
//
//            )
        }

        dialog.cancelDialog.setOnClickListener {
            dialog.dismiss()
        }
    }
}