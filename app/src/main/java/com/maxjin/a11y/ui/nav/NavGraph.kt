/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maxjin.a11y.ui.component.alert.AlertDialogScreen
import com.maxjin.a11y.ui.component.button.ButtonScreen
import com.maxjin.a11y.ui.component.toggleswitch.ToggleSwitchScreen
import com.maxjin.a11y.ui.home.HomeScreen
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

