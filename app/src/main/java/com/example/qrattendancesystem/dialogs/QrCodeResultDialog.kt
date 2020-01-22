package com.example.qrattendancesystem.dialogs

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.db.AppDatabase
import com.example.qrattendancesystem.db.models.QrResult
import com.example.qrattendancesystem.db.models.User
import com.google.gson.GsonBuilder
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
                    val gson = GsonBuilder().create()
                    var response: ServerResponse = gson.fromJson(body, ServerResponse::class.java)
                    //Save the result in database
                    val writeResult: QrResult = QrResult(
                        _id = qrResult?._id,
                        attendance_id = response._id,
                        class_name = qrResult?.class_name,
                        teacher_name = qrResult?.teacher_name,
                        class_date_time = response.class_date_time
                    )
                    AppDatabase.getAppDatabase(context)?.getQrResultDAO()?.insertQrResult(writeResult)
                }
            })
            dialog.dismiss()
            Toast.makeText(context, "You Marked Your Attendance Successfully.", Toast.LENGTH_LONG).show()
        }

        dialog.cancelDialog.setOnClickListener {
            dialog.dismiss()
        }
    }
}

//{ "class_date_time":"2020-01-22T02:42:21.303Z", "_id":"5e27b6c898c4190017abe577", "class_id":"5e233b54ddfbf1001707d943", "student_id":"5e27b24e5e810f001744cfbe", "__v":0 }

class ServerResponse(
    val _id: String,
    val class_date_time: String
)
