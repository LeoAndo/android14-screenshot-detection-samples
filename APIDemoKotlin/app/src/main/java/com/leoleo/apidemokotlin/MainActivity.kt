package com.leoleo.apidemokotlin

import android.app.Activity.ScreenCaptureCallback
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity() {
    private var screenshotTotalCount = 0
    private lateinit var txtScreenshotTotalCount: TextView
    private val screenCaptureCallback = ScreenCaptureCallback {
        Log.d("TAG", "Add logic to take action in your app.")
        txtScreenshotTotalCount.text = getString(R.string.format_str, ++screenshotTotalCount)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtScreenshotTotalCount = findViewById(R.id.txtScreenshotTotalCount)
        txtScreenshotTotalCount.text = getString(R.string.format_str, screenshotTotalCount)
        val switchSecure = findViewById<SwitchMaterial>(R.id.switchSecure)
        switchSecure.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) { // disable Screenshot features.
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
                )
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart: IN")
        // Pass in the callback created in the previous step
        // and the intended callback executor (e.g. Activity's mainExecutor).
        registerScreenCaptureCallback(mainExecutor, screenCaptureCallback)
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop: IN")
        unregisterScreenCaptureCallback(screenCaptureCallback)
    }
}