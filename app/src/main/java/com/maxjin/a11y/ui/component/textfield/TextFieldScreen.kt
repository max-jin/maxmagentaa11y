/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.AppTopAppBarState
import com.maxjin.a11y.ui.util.composable.CodeSnippet
import com.maxjin.a11y.ui.util.composable.CommentTextView
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.composable.LinkAction
import com.maxjin.a11y.ui.util.composable.TitleTextView
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.TextField

@Composable
fun TextFieldScreen(modifier: Modifier = Modifier, setTopBar: (AppTopAppBarState) -> Unit) {
    LaunchedEffect(Unit) {
        setTopBar(AppTopAppBarState(title = "TextField", linkAction = LinkAction(url = AppUtil.WebLinks.TEXTFIELD_URL)))
    }
    var textFieldValue by rememberSaveable { mutableStateOf("") }
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
                "Regular TextField", modifier = Modifier.padding(bottom = dimenB3)
            )
            TextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Regular TextField") },
                trailingIcon = {
                    if (textFieldValue.isNotEmpty()) Icon(
                        Icons.Filled.Clear, contentDescription = "Clear", modifier = Modifier.clickable(
                            onClick = { textFieldValue = "" },
                            role = Role.Button
                        )
                    )
                },
                isError = textFieldValue.length > 5
            )
            CommentTextView(
                text = "When using the TextField from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
                modifier = Modifier.padding(top = dimenB3)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimenB5),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            TitleTextView(
                "Password TextField", modifier = Modifier.padding(bottom = dimenB3, top = dimenB3)
            )
            var passwordValue by rememberSaveable { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }
            TextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (passwordVisible) "hide password" else "Show password"
                        )
                    }
                },
                isError = passwordValue.length > 5
            )
        }
        Text(
            text = "Code example",
            modifier = Modifier.padding(start = dimenB4, top = dimenB3, bottom = dimenB3),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall
        )
        CodeSnippet(codeText = TextField.CODE_SNIPPET)
        Spacer(modifier = Modifier.height(dimenB5))
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TextFieldScreenPreview() {
    MagentaA11yTheme {
        TextFieldScreen(setTopBar = {})
    }
}