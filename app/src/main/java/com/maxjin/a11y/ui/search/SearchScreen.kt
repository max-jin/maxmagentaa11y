/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.snap
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.maxjin.a11y.core.component.Component
import com.maxjin.a11y.ui.nav.NavDestination
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.RoundedCornerShapeMedium
import com.maxjin.a11y.ui.util.dimenB10
import com.maxjin.a11y.ui.util.dimenB2
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.dimenB6
import com.maxjin.a11y.ui.util.ext.verticalGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navigateUp: () -> Unit = {},
    navigateAction: (NavDestination) -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var onSearchBarActive by rememberSaveable { mutableStateOf(false) }
    Box(
        Modifier
            .fillMaxSize()
            .verticalGradient(),
    ) {
        // Talkback focus order sorts based on x and y position before considering z-index. The
        // extra Box with semantics and fillMaxWidth is a workaround to get the search bar to focus
        // before the content.
        Box(
            Modifier
                .semantics { isContainer = true }
                .zIndex(1f)
                .fillMaxWidth()) {
            IconButton(onClick = {
                if (onSearchBarActive) {
                    // keyboardController?.hide()
                    onSearchBarActive = false
                    searchQuery = ""
                } else {
                    navigateUp()
                }
            }, modifier = Modifier.padding(top = dimenB3)) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Search Component"
                )
            }
            SearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(start = dimenB6),
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { onSearchBarActive = false },
                active = onSearchBarActive,
                onActiveChange = { onSearchBarActive = it },
                placeholder = { Text("Type Component Name") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) Icon(
                        Icons.Filled.Clear, contentDescription = "Clear", modifier = Modifier.clickable(
                            onClick = { searchQuery = "" },
                            role = Role.Button
                        )
                    )
                },
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Transparent,
                    dividerColor = Color.Transparent,
                )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (searchQuery.isNotEmpty()) {
                        Component.allComponents.filter { component ->
                            component.name.contains(searchQuery, ignoreCase = true) || component.nameOptions?.any { it.contains(searchQuery, ignoreCase = true) } == true
                        }.forEach {
                            item {
                                ListItem(
                                    headlineContent = { Text(it.name) },
                                    modifier = Modifier
                                        //.padding(end = dimenB6)
                                        .clip(RoundedCornerShapeMedium)
                                        .clickable(
                                            onClick = {
                                                navigateAction(it.navDestination)
                                            },
                                            enabled = it.available
                                        ),
                                    supportingContent = if (!it.available) {
                                        { Text(text = "Work in progress") }
                                    } else null,
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.Transparent,
                                        headlineColor = if (it.available) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.outline,
                                        supportingColor = if (it.available) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.outline
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visibleState = remember { MutableTransitionState(initialState = false).apply { targetState = searchQuery.isEmpty() || !onSearchBarActive } },
            enter = slideInVertically { it } + fadeIn(),
            exit = fadeOut(animationSpec = snap())
        ) {
            LazyColumn(
                contentPadding = PaddingValues(start = dimenB6, top = dimenB10, end = dimenB6, bottom = dimenB5),
                verticalArrangement = Arrangement.spacedBy(dimenB2)
            ) {
                item {
                    Text(text = "Current available components", style = MaterialTheme.typography.titleMedium)
                }
                Component.allComponents.filter { it.available }.forEach {
                    item {
                        ListItem(
                            headlineContent = { Text(it.name) },
                            modifier = Modifier
                                .padding(end = dimenB6)
                                .clip(RoundedCornerShapeMedium)
                                .clickable(
                                    onClick = {
                                        navigateAction(it.navDestination)
                                    }
                                ),
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SearchScreenPreview() {
    MagentaA11yTheme {
        SearchScreen(navigateUp = {}, navigateAction = {})
    }
}