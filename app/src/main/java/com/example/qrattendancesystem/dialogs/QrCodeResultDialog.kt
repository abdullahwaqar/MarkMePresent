package com.example.qrattendancesystem.dialogs

import android.app.Dialog
import android.content.Context
import com.example.qrattendancesystem.db.models.QrResult

class QrCodeResultDialog(var context: Context) {

    private lateinit var dialog: Dialog
    private var qrResult : QrResult? = null

    init {
        initDialog()
    }

    private fun initDialog() {


    }
}