package com.example.qrattendancesystem.ui

import android.Manifest.permission
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.qrattendancesystem.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        checkPermissions()
    }

    private fun checkPermissions() {
        // Check if the user gave the permission
        if (ActivityCompat.checkSelfPermission(this, permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            goToMainActivity()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun goToMainActivity() {

    }
}
