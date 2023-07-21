/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.core.component.Component
import com.maxjin.a11y.core.component.ComponentType
import com.maxjin.a11y.ui.nav.NavDestination
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.RoundedCornerShapeLarge
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.dimenB0
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.dimenB6
import com.maxjin.a11y.ui.util.dimenB7
import com.maxjin.a11y.ui.util.ext.verticalGradient

// TODO ADD Compose Nav, Component screens - Button, Link, CheckBox, Toggle Switch,
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateAction: (NavDestination) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalGradient()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(dimenB5))
        Text(
            text = "Welcome to \nMagenta A11Y!",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimenB7),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(dimenB5))
        Component.allComponents.let { list ->
            list.filter { it.available }.let { availableList ->
                HomeCardTitle("Controls")
                HomeCard(availableList.filter { it.type == ComponentType.CONTROL }, navigateAction)
                HomeCardTitle("Notifications")
                HomeCard(availableList.filter { it.type == ComponentType.NOTIFICATION }, navigateAction)
            }
            HomeCardTitle("Work in progress")
            HomeCard(list.filter { !it.available }, navigateAction)
        }
        Spacer(modifier = Modifier.height(dimenB5))
    }
}

@Composable
fun HomeCardTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = dimenB6, top = dimenB4, bottom = dimenB3),
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun HomeCard(buttonList: List<Component>, navigateAction: (NavDestination) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimenB0, start = dimenB5, end = dimenB5, bottom = dimenB0)
            .clip(shape = RoundedCornerShapeLarge)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        buttonList.forEachIndexed { index, component ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clickable(onClick = {
                        navigateAction(component.navDestination)
                    }, role = Role.Button, enabled = component.available)
                    .background(
                        color = if (component.available) MaterialTheme.colorScheme.background else
                            MaterialTheme.colorScheme.inverseOnSurface
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = dimenB4, bottom = dimenB4, start = dimenB5)
                        .align(Alignment.CenterStart)
                ) {
                    Text(
                        text = component.name,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (component.available) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.outline
                    )
                }
                if (component.available) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = dimenB5),
                        imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                if (index < buttonList.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter), color = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HomeScreenPreview() {
    MagentaA11yTheme {
        HomeScreen(navigateAction = {})
    }
}