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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maxjin.a11y.ui.component.alert.AlertDialogScreen
import com.maxjin.a11y.ui.component.button.ButtonScreen
import com.maxjin.a11y.ui.component.carousel.CarouselScreen
import com.maxjin.a11y.ui.component.sheet.SheetScreen
import com.maxjin.a11y.ui.component.slider.SliderScreen
import com.maxjin.a11y.ui.component.snackbar.SnackbarScreen
import com.maxjin.a11y.ui.component.textfield.TextFieldScreen
import com.maxjin.a11y.ui.component.toggleswitch.ToggleSwitchScreen
import com.maxjin.a11y.ui.home.HomeScreen
import com.maxjin.a11y.ui.search.SearchScreen
import com.maxjin.a11y.ui.util.composable.AppTopAppBarState
import com.maxjin.a11y.ui.util.ext.navigateToComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    setTopBar: (AppTopAppBarState) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavHostController,
    startDestination: String = NavDestination.APP_HOME.id
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavDestination.APP_HOME.id) {
            HomeScreen(modifier = modifier, setTopBar = setTopBar, navigateAction = { navController.navigateToComponent(it) }, scrollBehavior = scrollBehavior )
        }
        composable(route = NavDestination.APP_SEARCH.id) {
            SearchScreen(modifier = modifier, navigateUp = { navController.navigateUp() }, navigateAction = { navController.navigateToComponent(it) }, setTopBar = setTopBar)
        }
        composable(route = NavDestination.COMPONENT_BUTTON.id) {
            ButtonScreen(modifier = modifier, setTopBar = setTopBar)
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
        composable(route = NavDestination.COMPONENT_SWITCH.id) {
            ToggleSwitchScreen(modifier = modifier, setTopBar = setTopBar)
        }
        composable(route = NavDestination.COMPONENT_ALERT_DIALOG.id) {
            AlertDialogScreen(modifier = modifier, setTopBar = setTopBar)
        }
        composable(route = NavDestination.COMPONENT_SNACKBAR.id) {
            SnackbarScreen(modifier = modifier, setTopBar = setTopBar)
        }
        composable(route = NavDestination.COMPONENT_CAROUSEL.id) {
            CarouselScreen(modifier = modifier, setTopBar = setTopBar)
        }
        composable(route = NavDestination.COMPONENT_SHEET.id) {
            SheetScreen(modifier = modifier, setTopBar = setTopBar)
        }
        composable(route = NavDestination.COMPONENT_TEXT_FIELD.id) {
            TextFieldScreen(modifier = modifier, setTopBar = setTopBar)
        }
        composable(route = NavDestination.COMPONENT_SLIDER.id) {
            SliderScreen(modifier = modifier, setTopBar = setTopBar)
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