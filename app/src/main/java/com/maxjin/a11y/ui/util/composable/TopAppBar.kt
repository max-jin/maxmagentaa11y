/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.util.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.maxjin.a11y.ui.nav.NavDestination
import com.maxjin.a11y.ui.util.ext.navigateToComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLargeTopBar(
    appTopAppBarState: AppTopAppBarState,
    navController: NavController,
    urlHandler: UriHandler,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    if (appTopAppBarState.enable) {
        LargeTopAppBar(
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent
            ),
            title = {
                Text(
                    text = appTopAppBarState.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = if (appTopAppBarState.enableNavUp) {
                {
                    IconButton(onClick = navController::navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                }
            } else {
                { }
            },
            actions = if (appTopAppBarState.enableSearch) {
                {
                    IconButton(onClick = { navController.navigateToComponent(NavDestination.APP_SEARCH) }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Component"
                        )
                    }
                }
            } else if (appTopAppBarState.linkAction?.enable == true) {
                {
                    IconButton(onClick = {
                        // TODO open link
                        urlHandler.openUri(appTopAppBarState.linkAction.url)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Link,
                            contentDescription = "Magenta A11y ${appTopAppBarState.title} page"
                        )
                    }
                }
            } else {
                {}
            },
            scrollBehavior = scrollBehavior
        )
    }
}

data class AppTopAppBarState(
    val enable: Boolean = true,
    val title: String = "",
    val enableNavUp: Boolean = true,
    val enableSearch: Boolean = false,
    val linkAction: LinkAction? = null
)

data class LinkAction(
    val enable: Boolean = true,
    val url: String = ""
)