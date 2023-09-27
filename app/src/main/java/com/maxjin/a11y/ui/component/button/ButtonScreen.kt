/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.button

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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.ui.theme.MagentaA11yThemePreview
import com.maxjin.a11y.ui.util.composable.AppTopAppBarState
import com.maxjin.a11y.ui.util.composable.CodeSnippet
import com.maxjin.a11y.ui.util.composable.CommentTextView
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.composable.LinkAction
import com.maxjin.a11y.ui.util.composable.TitleTextView
import com.maxjin.a11y.ui.util.dimenB2
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.Button

@Composable
fun ButtonScreen(modifier: Modifier, setTopBar: (AppTopAppBarState) -> Unit) {
    LaunchedEffect(Unit) {
        setTopBar(AppTopAppBarState(title = "Button", linkAction = LinkAction(url = AppUtil.WebLinks.BUTTON_URL)))
    }
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
                "Regular button", modifier = Modifier.padding(bottom = dimenB3)
            )
            Button(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Text(text = "Button", modifier = Modifier.padding(start = dimenB2))
            }
            TitleTextView(
                "Disabled button", modifier = Modifier.padding(bottom = dimenB3, top = dimenB3)
            )
            Button(onClick = { /*TODO*/ }, enabled = false) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Text(text = "Button", modifier = Modifier.padding(start = dimenB2))
            }
            CommentTextView(
                text = "When using the button from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
                modifier = Modifier.padding(top = dimenB3)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimenB5),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            TitleTextView(
                "Icon button", modifier = Modifier.padding(bottom = dimenB3, top = dimenB5)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.ThumbUp, contentDescription = "thumb up", tint = MaterialTheme.colorScheme.primary)
            }
            CommentTextView(
                text = "With the IconButton, make sure the icon has the proper content description for the accessibility.",
                modifier = Modifier.padding(top = dimenB3)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimenB5),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            TitleTextView(
                "Custom button (Modifier.Clickable)", modifier = Modifier.padding(bottom = dimenB3, top = dimenB5)
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
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
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
            CommentTextView(
                text = "For the custom button component with using Modifier clickable instead of native Composable, make sure the clickable block has the proper role - Button assigned and content description if needed.",
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
        CodeSnippet(codeText = Button.CODE_SNIPPET)
        Spacer(modifier = Modifier.height(dimenB5))
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ButtonScreenPreview() {
    MagentaA11yThemePreview {
        ButtonScreen(modifier = Modifier, setTopBar = {})
    }
}