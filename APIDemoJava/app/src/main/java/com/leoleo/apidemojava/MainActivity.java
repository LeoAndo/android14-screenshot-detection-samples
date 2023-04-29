package com.leoleo.apidemojava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {
    private int screenshotTotalCount = 0;
    private TextView txtScreenshotTotalCount;
    private final Activity.ScreenCaptureCallback screenCaptureCallback =
            () -> {
                Log.d("TAG", "Add logic to take action in your app.");
                txtScreenshotTotalCount.setText(getString(R.string.format_str, ++screenshotTotalCount));
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtScreenshotTotalCount = findViewById(R.id.txtScreenshotTotalCount);
        txtScreenshotTotalCount.setText(getString(R.string.format_str, screenshotTotalCount));
        final SwitchMaterial switchSecure = findViewById(R.id.switchSecure);
        switchSecure.setOnCheckedChangeListener((compoundButton, checked) -> {
            if (checked) { // disable Screenshot features.
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_SECURE,
                        WindowManager.LayoutParams.FLAG_SECURE
                );
            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart: IN");
        // Pass in the callback created in the previous step
        // and the intended callback executor (e.g. Activity's mainExecutor).
        registerScreenCaptureCallback(getMainExecutor(), screenCaptureCallback);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: IN");
        unregisterScreenCaptureCallback(screenCaptureCallback);
    }
}