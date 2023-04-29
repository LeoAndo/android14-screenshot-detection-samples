package com.leoleo.apidemocompose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leoleo.apidemocompose.ui.theme.APIDemoComposeTheme

@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    flagSecure: Boolean = false,
    content: @Composable () -> Unit,
) {
    APIDemoComposeTheme(flagSecure =flagSecure) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}