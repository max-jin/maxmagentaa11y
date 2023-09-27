/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.snackbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.AppTopAppBarState
import com.maxjin.a11y.ui.util.composable.CodeSnippet
import com.maxjin.a11y.ui.util.composable.CommentTextView
import com.maxjin.a11y.ui.util.composable.LinkAction
import com.maxjin.a11y.ui.util.composable.TitleTextView
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.Snackbar
import kotlinx.coroutines.launch

@Composable
fun SnackbarScreen(modifier: Modifier = Modifier, setTopBar: (AppTopAppBarState) -> Unit) {
    LaunchedEffect(Unit) {
        setTopBar(AppTopAppBarState(title = "Snackbar", linkAction = LinkAction(url = AppUtil.WebLinks.SNACKBAR_URL)))
    }
    // This components provides only the visuals of the Snackbar. If you need to show a Snackbar with defaults on the screen,
    // use SnackbarHostState.showSnackbar:
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                // custom snackbar with the custom action button
                Snackbar(
                    modifier = Modifier.padding(12.dp),
                    action = data.visuals.actionLabel?.let {
                        { TextButton(onClick = { data.dismiss() }) { Text(it, color = MaterialTheme.colorScheme.background) } }
                    },
                    dismissAction = if (data.visuals.withDismissAction) {
                        { IconButton(onClick = { data.dismiss() }) { Icon(Icons.Filled.Close, contentDescription = "dismiss") } }
                    } else null
                ) {
                    Text(data.visuals.message)
                }
            }
        },
        containerColor = Color.Transparent,
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimenB4)
                ) {
                    TitleTextView(
                        "Snackbar - No actions", modifier = Modifier.padding(bottom = dimenB3)
                    )
                    Button(onClick = {
                        // show snackbar as a suspend function
                        scope.launch {
                            snackbarHostState.showSnackbar("Snackbar Showing", actionLabel = null, withDismissAction = false, duration = SnackbarDuration.Short)
                        }
                    }) {
                        Text(text = "Snackbar - No actions")
                    }
                    Spacer(modifier = Modifier.height(dimenB4))
                    TitleTextView(
                        "Snackbar - With Dismiss Actions", modifier = Modifier.padding(bottom = dimenB3)
                    )
                    Button(onClick = {
                        // show snackbar as a suspend function
                        scope.launch {
                            snackbarHostState.showSnackbar("Snackbar Showing", actionLabel = null, withDismissAction = true, duration = SnackbarDuration.Indefinite)
                        }
                    }) {
                        Text(text = "Snackbar - With Dismiss Action")
                    }
                    Spacer(modifier = Modifier.height(dimenB4))

                    TitleTextView(
                        "Snackbar - With Custom Action", modifier = Modifier.padding(bottom = dimenB3)
                    )
                    Button(onClick = {
                        // show snackbar as a suspend function
                        scope.launch {
                            snackbarHostState.showSnackbar("Snackbar Showing", actionLabel = "Close", withDismissAction = false, duration = SnackbarDuration.Indefinite)
                        }
                    }) {
                        Text(text = "Snackbar - With Custom Action")
                    }
                    Spacer(modifier = Modifier.height(dimenB4))

                    TitleTextView(
                        "Snackbar - With Both Actions", modifier = Modifier.padding(bottom = dimenB3)
                    )
                    Button(onClick = {
                        // show snackbar as a suspend function
                        scope.launch {
                            snackbarHostState.showSnackbar("Snackbar Showing", actionLabel = "Close", withDismissAction = true, duration = SnackbarDuration.Indefinite)
                        }
                    }) {
                        Text(text = "Snackbar - With Both Actions")
                    }
                    Spacer(modifier = Modifier.height(dimenB3))

                    CommentTextView(
                        text = "When using the Snackbar from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
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
                CodeSnippet(codeText = Snackbar.SNACKBAR_CODE_SNIPPET)
                Spacer(modifier = Modifier.height(dimenB5))
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SnackbarScreenPreview() {
    MagentaA11yTheme {
        SnackbarScreen(setTopBar = {})
    }
}