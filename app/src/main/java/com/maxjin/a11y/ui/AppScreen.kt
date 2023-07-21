/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.maxjin.a11y.ui.nav.NavGraph
import com.maxjin.a11y.ui.theme.MagentaA11yTheme

/**
 * App Main Screen - Top Layer of Composable
 *
 * 1. Top bar - foldable
 * 2. SearchBar
 * 3. Nav
 * 4. Bottom Sheet
 */
@Composable
fun AppScreen(
    context: Context,
    onFinish: () -> Unit
) {

    // Navigation Controller
    val navController = rememberNavController()

    MagentaA11yTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            NavGraph(navController = navController)
        }
    }
}