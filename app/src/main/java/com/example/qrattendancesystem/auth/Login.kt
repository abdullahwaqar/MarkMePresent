package com.example.qrattendancesystem.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import com.example.qrattendancesystem.R
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {

    companion object {
        private var loginBtn : Button? = null
        private var nameField : TextInputEditText? = null
        private var rollIdField: TextInputEditText? = null
        private var passwordField: TextInputEditText? = null
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
                Toast.makeText(this.applicationContext, "One the field is empty", Toast.LENGTH_SHORT).show()
            } else {
                registerUserOnServer(nameField!!.text?.toString(), rollIdField!!.text?.toString(), passwordField!!.text?.toString())
            }
        }
    }

    private fun registerUserOnServer(name: String?, rollId: String?, password: String?) {
        println(name + rollId + password)
    }
}
