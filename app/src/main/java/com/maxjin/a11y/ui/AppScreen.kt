package com.maxjin.a11y.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxjin.a11y.ui.home.HomeScreen
import com.maxjin.a11y.ui.theme.MagentaA11yTheme

@Composable
fun AppScreen(
    context: Context,
    onFinish: () -> Unit
) {
    MagentaA11yTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            HomeScreen()
        }
    }
}