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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.CodeSnippet
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.composable.LargeTopBar
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.ext.verticalGradient
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.TextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldScreen(navigateUp: () -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val urlHandler = LocalUriHandler.current
    var textFieldValue by rememberSaveable { mutableStateOf("") }
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalGradient(),
        topBar = {
            LargeTopBar(
                title = "TextField",
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
                        urlHandler.openUri(AppUtil.WebLinks.TEXTFIELD_URL)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Link,
                            contentDescription = "Magenta A11y TextField page"
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
                    TextFieldTitleView(
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
                    TextFieldCommentsView(
                        text = "When using the TextField from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
                        modifier = Modifier.padding(top = dimenB3)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimenB5),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    TextFieldTitleView(
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
    )
}

@Composable
fun TextFieldTitleView(
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
fun TextFieldCommentsView(
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
fun TextFieldScreenPreview() {
    MagentaA11yTheme {
        TextFieldScreen(navigateUp = {})
    }
}