/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.composable.LargeTopBar
import com.maxjin.a11y.ui.util.dimenB2
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.ext.verticalGradient
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.Button

// TODO Add topBar, examples, magentaA11y link, code snippets
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonScreen(navigateUp: () -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val urlHandler = LocalUriHandler.current
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalGradient(),
        topBar = {
            LargeTopBar(
                title = "Button",
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
                        urlHandler.openUri(AppUtil.WebLinks.BUTTON_URL)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Link,
                            contentDescription = "Magenta A11y Button page"
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
                    ButtonTitleView(
                        "Regular button", modifier = Modifier.padding(bottom = dimenB3)
                    )
                    Button(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                        Text(text = "Button", modifier = Modifier.padding(start = dimenB2))
                    }
                    ButtonCommentsView(
                        text = "When using the regular button from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
                        modifier = Modifier.padding(top = dimenB3)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimenB4),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    ButtonTitleView(
                        "Icon button", modifier = Modifier.padding(bottom = dimenB3, top = dimenB4)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ThumbUp, contentDescription = "thumb up", tint = MaterialTheme.colorScheme.primary)
                    }
                    ButtonCommentsView(
                        text = "With the IconButton, make sure the icon has the proper content description for the accessibility.",
                        modifier = Modifier.padding(top = dimenB3)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimenB4),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    ButtonTitleView(
                        "Custom button (Modifier.Clickable)", modifier = Modifier.padding(bottom = dimenB3, top = dimenB4)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .clip(RoundedCornerShape(dimenB3))
                            .clickable(onClick = {}, role = Role.Button)
                            .background(color = MaterialTheme.colorScheme.primary)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = dimenB3, bottom = dimenB3, start = dimenB4)
                                .align(Alignment.CenterStart)
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = null, tint = MaterialTheme.colorScheme.background)
                            Text(
                                text = "Button",
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start = dimenB3),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.background
                            )
                        }
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = dimenB4),
                            imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.background
                        )
                    }
                    ButtonCommentsView(
                        text = "For the custom button component with using Modifier clickable instead of native Composable, make sure the clickable block has the proper role - Button assigned and content description if needed.",
                        modifier = Modifier.padding(top = dimenB3)
                    )
                }
                ButtonTitleView("Code example", modifier = Modifier.padding(start = dimenB4, top = dimenB4, bottom = dimenB3))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimenB4)
                        .background(color = MaterialTheme.colorScheme.onPrimaryContainer, shape = RoundedCornerShape(size = 8.dp)),
                ) {
                    Text(
                        text = Button.CODE_SNIPPET,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimenB4),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(dimenB5))
            }
        }
    )
}

@Composable
fun ButtonTitleView(
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
fun ButtonCommentsView(
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
fun ButtonScreenPreview() {
    MagentaA11yTheme {
        ButtonScreen(navigateUp = {})
    }
}