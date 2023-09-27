/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.compose.rememberNavController
import com.maxjin.a11y.ui.nav.NavGraph
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.AppLargeTopBar
import com.maxjin.a11y.ui.util.composable.AppTopAppBarState
import com.maxjin.a11y.ui.util.ext.verticalGradient

/**
 * App Main Screen - Top Layer of Composable
 *
 * 1. Top bar - foldable
 * 2. SearchBar
 * 3. Nav
 * 4. Bottom Sheet
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    context: Context,
    onFinish: () -> Unit
) {

    // Navigation Controller
    val navController = rememberNavController()

    var appTopAppBarState by remember { mutableStateOf(AppTopAppBarState()) }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val urlHandler = LocalUriHandler.current

    MagentaA11yTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalGradient(), color = Color.Transparent
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .verticalGradient(),
                topBar = {
                    AppLargeTopBar(appTopAppBarState = appTopAppBarState, navController = navController, urlHandler = urlHandler, scrollBehavior = scrollBehavior)
                },
                content = { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        setTopBar = {
                            appTopAppBarState = it
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            )
        }
    }
}