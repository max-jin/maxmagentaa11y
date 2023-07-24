/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.util.ext

import androidx.navigation.NavController
import com.maxjin.a11y.ui.nav.NavDestination

fun NavController.navigateToComponent(destination: NavDestination) {
    this.navigate(destination.id)
}