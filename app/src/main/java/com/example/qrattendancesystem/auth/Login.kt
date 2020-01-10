package com.example.qrattendancesystem.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qrattendancesystem.MainActivity
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.db.AppDatabase
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class Login : AppCompatActivity() {

    companion object {
        private var loginBtn: Button? = null
        private var nameField: TextInputEditText? = null
        private var rollIdField: TextInputEditText? = null
        private var passwordField: TextInputEditText? = null

        private val client = OkHttpClient()
        private val base_url = "http://192.168.8.102:3000/api/createstudent"
        private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

        private var user: User? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.login_button)
        nameField = findViewById(R.id.nameInput)
        rollIdField = findViewById(R.id.rollIdInput)
        passwordField = findViewById(R.id.passwordField)

        loginBtn!!.setOnClickListener {
            println(nameField!!.text)
            if (nameField!!.text?.isEmpty()!! or rollIdField!!.text?.isEmpty()!! or passwordField!!.text?.isEmpty()!!) {
                Toast.makeText(
                    this.applicationContext,
                    "One the field is empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                registerUserOnServer(
                    nameField!!.text?.toString(),
                    rollIdField!!.text?.toString(),
                    passwordField!!.text?.toString()
                )
            }
        }
    }

    private fun registerUserOnServer(name: String?, rollId: String?, password: String?) {
        println(name + rollId + password)
        var document = JSONObject()
        document.put("name", name)
        document.put("roll_id", rollId)
        document.put("password", password)
        val request = Request.Builder().url(base_url).post(document.toString().toRequestBody(JSON)).build()

        // Cannot run on main thread do this -_-
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                user = gson.fromJson(body, User::class.java)
            }
        })
        val writeUser : com.example.qrattendancesystem.db.models.User = com.example.qrattendancesystem.db.models.User(
            _id = user?._id,
            name = user?.name,
            roll_id = user?.roll_id,
            password = user?.password
        )
        AppDatabase.getAppDatabase(this@Login)?.getUserDAO()?.insertUser(user = writeUser) // write the user in database
        startActivity(Intent(this@Login, MainActivity::class.java))
        finish()

    }
}

class User(
    val _id: String,
    val name: String,
    val roll_id: String,
    val password: String
    )