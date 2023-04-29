package com.leoleo.apidemocompose

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var screenshotCount by rememberSaveable { mutableStateOf(0) }
    var flagSecure by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val mainExecutor = context.mainExecutor
    val activity = (LocalView.current.context as Activity)
    DisposableEffect(key1 = activity, effect = {
        Log.d("TAG", "DisposableEffect: IN")
        val screenCaptureCallback = Activity.ScreenCaptureCallback {
            Log.d("TAG", "Add logic to take action in your app.")
            screenshotCount++
        }
        activity.registerScreenCaptureCallback(mainExecutor, screenCaptureCallback)
        onDispose {
            Log.d("TAG", "DisposableEffect: call onDispose")
            activity.unregisterScreenCaptureCallback(screenCaptureCallback)
        }
    })
    MainScreenStateless(
        modifier = modifier,
        flagSecure = flagSecure,
        screenshotCount = screenshotCount,
        onCheckedChange = { flagSecure = it }
    )
}

@Composable
private fun MainScreenStateless(
    modifier: Modifier = Modifier,
    flagSecure: Boolean,
    screenshotCount: Int,
    onCheckedChange: (Boolean) -> Unit,
) {
    AppSurface(modifier = modifier, flagSecure = flagSecure) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Screenshot Total Count: $screenshotCount")
            val text = if (flagSecure) "FLAG_SECURE: ON" else "FLAG_SECURE: OFF"
            Text(text = text)
            Switch(checked = flagSecure, onCheckedChange = { onCheckedChange(it) })
        }
    }
}

@PreviewPhoneDevice
@Composable
private fun Perv_MainScreen_flagSecure_true() {
    MainScreenStateless(flagSecure = true, onCheckedChange = {}, screenshotCount = 0)
}

@PreviewPhoneDevice
@Composable
private fun Perv_MainScreen_flagSecure_false() {
    MainScreenStateless(flagSecure = false, onCheckedChange = {}, screenshotCount = 1)
}