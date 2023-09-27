/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.alert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import com.maxjin.a11y.util.component.AlertDialog

@Composable
fun AlertDialogScreen(modifier: Modifier = Modifier, setTopBar: (AppTopAppBarState) -> Unit) {
    LaunchedEffect(Unit) {
        setTopBar(AppTopAppBarState(title = "Alert dialog", linkAction = LinkAction(url = AppUtil.WebLinks.ALERT_DIALOG_URL)))
    }
    var onAlertDialogOpen by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimenB4)
        ) {
            TitleTextView(
                "Regular Alert Dialog", modifier = Modifier.padding(bottom = dimenB3)
            )
            Button(onClick = { onAlertDialogOpen = true }) {
                Text(text = "Open Alert Dialog")
            }
            CommentTextView(
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
        CodeSnippet(codeText = AlertDialog.ALERT_DIALOG_CODE_SNIPPET)
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

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun AlertDialogScreenPreview() {
    MagentaA11yTheme {
        AlertDialogScreen(setTopBar = {})
    }
}