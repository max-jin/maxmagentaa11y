/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.snap
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maxjin.a11y.ui.component.alert.AlertDialogScreen
import com.maxjin.a11y.ui.component.button.ButtonScreen
import com.maxjin.a11y.ui.component.toggleswitch.ToggleSwitchScreen
import com.maxjin.a11y.ui.home.HomeScreen
import com.maxjin.a11y.ui.search.SearchScreen
import com.maxjin.a11y.ui.util.ext.navigateToComponent

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = NavDestination.APP_HOME.id
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavDestination.APP_HOME.id) {
            HomeScreen(navigateAction = { navController.navigateToComponent(it) })
        }
        composable(route = NavDestination.APP_SEARCH.id) {
            SearchScreen(navigateUp = { navController.navigateUp() }, navigateAction = { navController.navigateToComponent(it) })
        }
        composable(route = NavDestination.COMPONENT_BUTTON.id) {
            ButtonScreen(navigateUp = { navController.navigateUp() })
        }
        composable(route = NavDestination.COMPONENT_CHECKBOX.id) {
            // TODO
        }
        composable(route = NavDestination.COMPONENT_LINK.id) {
            // TODO
        }
        composable(route = NavDestination.COMPONENT_RADIO_BUTTON.id) {
            // TODO
        }
        composable(route = NavDestination.COMPONENT_TEXT_FIELD.id) {
            // TODO
        }
        composable(route = NavDestination.COMPONENT_SWITCH.id) {
            ToggleSwitchScreen(navigateUp = { navController.navigateUp() })
        }
        composable(route = NavDestination.COMPONENT_ALERT_DIALOG.id) {
            AlertDialogScreen(navigateUp = { navController.navigateUp() })
        }
    }
}


@Suppress("unused")
@Composable
fun NavAnimated(
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visibleState = remember { MutableTransitionState(initialState = false).apply { targetState = true } },
        enter = slideInVertically { it } + fadeIn(),
        exit = fadeOut(animationSpec = snap()),
        content = { content() }
    )
}