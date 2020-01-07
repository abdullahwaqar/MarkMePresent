package com.example.qrattendancesystem.ui

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.qrattendancesystem.MainActivity
import com.example.qrattendancesystem.R

class SplashScreen : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        checkPermissions()
    }

    private fun checkPermissions() {
        //* */ Check if the user gave the permission
        if (ActivityCompat.checkSelfPermission(this, permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            goToMainActivity()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //* If user grants permission go to main activity
                goToMainActivity()
            } else if (isUserPermanentlyDenied()) {
                showGoToAppSettingDialog()
            }
        } //* If the request code is what we need
    }

    private fun showGoToAppSettingDialog() {
        AlertDialog.Builder(this).setTitle("Grant Permissions.")
            .setMessage("Need Camera permission to scan QR Code.")
            .setPositiveButton("Grant"){ dialog, which ->
                goToAppSettings()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(
                    this,
                    "Need Camera Permissions to make this application functional.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }.show()
    }

    private fun goToAppSettings() {
        var intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun isUserPermanentlyDenied(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            shouldShowRequestPermissionRationale(permission.CAMERA).not()
        } else {
            return false
        }
    }

    private fun goToMainActivity() {
        //* Load main activity if all things are normal
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        //* Check the permissions again
        checkPermissions()
    }
}
