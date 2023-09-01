/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.CodeSnippet
import com.maxjin.a11y.ui.util.composable.LargeTopBar
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.ext.verticalGradient
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.Sheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetScreen(navigateUp: () -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val urlHandler = LocalUriHandler.current

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalGradient(),
        topBar = {
            LargeTopBar(
                title = "Sheet",
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // TODO open link
                        urlHandler.openUri(AppUtil.WebLinks.ALERT_DIALOG_URL)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Link,
                            contentDescription = "Magenta A11y sheet page"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimenB4)
                ) {
                    SheetTitleView(
                        "Bottom Sheet", modifier = Modifier.padding(bottom = dimenB3)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = skipPartiallyExpanded,
                                role = Role.Checkbox,
                                onValueChange = { checked -> skipPartiallyExpanded = checked }
                            )
                            .padding(top = dimenB4, bottom = dimenB4),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
                        Spacer(Modifier.width(dimenB3))
                        Text("Skip partially expanded State", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleSmall)
                    }
                    Button(onClick = { openBottomSheet = !openBottomSheet }) {
                        Text(text = "Show Bottom Sheet")
                    }
                    SheetCommentsView(
                        text = "When using the Bottom Sheet from native composable (ModalBottomSheet), the default behavior will cover the accessibility, no extra actions are needed.",
                        modifier = Modifier.padding(top = dimenB3)
                    )
                }
                Text(
                    text = "Code example",
                    modifier = Modifier.padding(start = dimenB4, top = dimenB3, bottom = dimenB3),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                CodeSnippet(codeText = Sheet.SHEET_CODE_SNIPPET)
                Spacer(modifier = Modifier.height(dimenB5))
            }
            if (openBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { openBottomSheet = false },
                    sheetState = bottomSheetState,
                    modifier = Modifier.padding(top = dimenB4)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                            // you must additionally handle intended state cleanup, if any.
                            onClick = {
                                scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                    if (!bottomSheetState.isVisible) {
                                        openBottomSheet = false
                                    }
                                }
                            }
                        ) {
                            Text("Hide Bottom Sheet")
                        }
                        LazyColumn {
                            items(50) {
                                ListItem(
                                    headlineContent = { Text("Sheet item $it") },
                                    leadingContent = {
                                        Icon(
                                            Icons.Default.Star,
                                            contentDescription = "Localized description"
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun SheetTitleView(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun SheetCommentsView(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SheetScreenPreview() {
    MagentaA11yTheme {
        SheetScreen(navigateUp = {})
    }
}