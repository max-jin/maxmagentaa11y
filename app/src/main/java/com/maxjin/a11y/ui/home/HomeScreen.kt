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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.core.component.Component
import com.maxjin.a11y.core.component.ComponentType
import com.maxjin.a11y.ui.nav.NavDestination
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.RoundedCornerShapeLarge
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.composable.LargeTopBar
import com.maxjin.a11y.ui.util.dimenB0
import com.maxjin.a11y.ui.util.dimenB1
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.ext.verticalGradient

// TODO ADD Component screens - Button, Link, CheckBox, Toggle Switch,
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateAction: (NavDestination) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val topAppBarOnCollapse by remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.2f } }

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalGradient(),
        topBar = {
            LargeTopBar(
                title = if (topAppBarOnCollapse) "Magenta A11y" else "Welcome to \nMagenta A11y",
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { navigateAction(NavDestination.APP_SEARCH) }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Component"
                        )
                    }
                },
            )
        },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                HomeCardTitle(
                    "Accessibility checklist", modifier = Modifier.padding(start = dimenB4, bottom = dimenB1),
                    style = MaterialTheme.typography.titleMedium
                )
                Component.allComponents.let { list ->
                    list.filter { it.available }.let { availableList ->
                        HomeCardTitle("Controls", modifier = Modifier.padding(start = dimenB5, top = dimenB4, bottom = dimenB3))
                        HomeCard(availableList.filter { it.type == ComponentType.CONTROL }, navigateAction)
                        HomeCardTitle("Notifications", modifier = Modifier.padding(start = dimenB5, top = dimenB4, bottom = dimenB3))
                        HomeCard(availableList.filter { it.type == ComponentType.NOTIFICATION }, navigateAction)
                    }
                    HomeCardTitle("Work in progress", modifier = Modifier.padding(start = dimenB5, top = dimenB4, bottom = dimenB3))
                    HomeCard(list.filter { !it.available }, navigateAction)
                }
                Spacer(modifier = Modifier.height(dimenB5))
                Column(
                    modifier = Modifier.padding(horizontal = dimenB3),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "The accessibility acceptance criteria testing checklist generated by T-Mobile - Accessibility Resource Center",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(text = "Developed by MAX JIN", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.tertiary)
                    Text(text = "App Version: 1.0.0", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.tertiary)
                }
                Spacer(modifier = Modifier.height(dimenB5))
            }
        }
    )
}

@Composable
fun HomeCardTitle(
    title: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onBackground,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        textAlign = textAlign,
        color = color,
        style = style
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