/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.LargeTopBar
import com.maxjin.a11y.ui.util.dimenB2
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.ext.copyToClipBoard
import com.maxjin.a11y.ui.util.ext.verticalGradient
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.AlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogScreen(navigateUp: () -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val urlHandler = LocalUriHandler.current
    val context = LocalContext.current

    var onAlertDialogOpen by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalGradient(),
        topBar = {
            LargeTopBar(
                title = "Alert dialog",
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
                            contentDescription = "Magenta A11y Alert Dialog page"
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
                    AlertDialogTitleView(
                        "Regular Alert Dialog", modifier = Modifier.padding(bottom = dimenB3)
                    )
                    Button(onClick = { onAlertDialogOpen = true }) {
                        Text(text = "Open Alert Dialog")
                    }
                    AlertDialogCommentsView(
                        text = "When using the AlertDialog from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimenB4)
                        .background(color = MaterialTheme.colorScheme.onPrimaryContainer, shape = RoundedCornerShape(size = 8.dp)),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy, contentDescription = "copy code",
                        tint = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .padding(dimenB2)
                            .align(Alignment.TopEnd)
                            .size(20.dp)
                            .clickable(
                                onClick = { context.copyToClipBoard(AlertDialog.ALERT_DIALOG_CODE_SNIPPET) },
                                role = Role.Button
                            )
                    )
                    Text(
                        text = AlertDialog.ALERT_DIALOG_CODE_SNIPPET,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimenB4),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(dimenB5))

                if (onAlertDialogOpen) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = {
                            Text(text = "Alert Dialog")
                        },
                        text = {
                            Text(text = "Display the alert message here.")
                        },
                        confirmButton = {
                            Button(onClick = { onAlertDialogOpen = !onAlertDialogOpen }) { Text(text = "Ok") }
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun AlertDialogTitleView(
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
fun AlertDialogCommentsView(
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
fun AlertDialogScreenPreview() {
    MagentaA11yTheme {
        AlertDialogScreen(navigateUp = {})
    }
}