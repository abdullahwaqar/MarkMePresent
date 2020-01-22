package com.example.qrattendancesystem.dialogs

import android.app.Dialog
import android.content.Context
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.db.AppDatabase
import com.example.qrattendancesystem.db.models.QrResult
import com.example.qrattendancesystem.db.models.User
import kotlinx.android.synthetic.main.qr_result_layout.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class QrCodeResultDialog(var context: Context) {

    companion object {
        private const val base_url = "https://temp-markmepresent.herokuapp.com/api/markattendance"
        private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

    }

    private lateinit var dialog: Dialog
    private var qrResult: DialogResult? = null
    private var user: User? = null

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
            user = AppDatabase.getAppDatabase(context!!)?.getUserDAO()?.getUser()
            val request = Request.Builder().url("${base_url}/${qrResult?._id}/${user?._id}").post(
                "".toRequestBody(
                    JSON
                )
            ).build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    println(body)
                }

            })

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