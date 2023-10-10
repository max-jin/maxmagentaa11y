/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.chip

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.ui.theme.MagentaA11yThemePreview
import com.maxjin.a11y.ui.util.composable.AppTopAppBarState
import com.maxjin.a11y.ui.util.composable.CodeSnippet
import com.maxjin.a11y.ui.util.composable.CommentTextView
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.composable.LinkAction
import com.maxjin.a11y.ui.util.composable.TitleTextView
import com.maxjin.a11y.ui.util.dimenB1
import com.maxjin.a11y.ui.util.dimenB2
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.Chip

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ChipScreen(modifier: Modifier = Modifier, setTopBar: (AppTopAppBarState) -> Unit) {
    LaunchedEffect(Unit) {
        setTopBar(AppTopAppBarState(title = "Chip", linkAction = LinkAction(url = AppUtil.WebLinks.CHIP_URL)))
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
                "Assist Chip", modifier = Modifier.padding(bottom = dimenB2)
            )
            CommentTextView(
                text = "Assist chips represent smart or automated actions that can span multiple apps.",
                modifier = Modifier.padding(bottom = dimenB3)
            )
            AssistChip(
                onClick = { /* Do something! */ },
                label = { Text("Assist Chip") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
            Spacer(modifier = Modifier.height(dimenB3))

            TitleTextView(
                "Filter Chip", modifier = Modifier.padding(bottom = dimenB2)
            )
            CommentTextView(
                text = "Filter chips represent filters for a collection.",
                modifier = Modifier.padding(bottom = dimenB3)
            )
            var selected by remember { mutableStateOf(false) }
            FilterChip(
                selected = selected,
                onClick = { selected = !selected },
                label = { Text("Filter chip") },
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Localized Description",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )
            Spacer(modifier = Modifier.height(dimenB3))

            TitleTextView(
                "Input Chip", modifier = Modifier.padding(bottom = dimenB2)
            )
            CommentTextView(
                text = "Input chips represent discrete pieces of information entered by a user.",
                modifier = Modifier.padding(bottom = dimenB3)
            )
            var selectedInput by remember { mutableStateOf(false) }
            InputChip(
                selected = selectedInput,
                onClick = { selectedInput = !selectedInput },
                label = { Text("Input Chip") },
                avatar = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Localized description",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            )
            Spacer(modifier = Modifier.height(dimenB3))

            TitleTextView(
                "Suggestion Chip", modifier = Modifier.padding(bottom = dimenB2)
            )
            CommentTextView(
                text = "Suggestion chips help narrow a user’s intent by presenting dynamically generated suggestions.",
                modifier = Modifier.padding(bottom = dimenB3)
            )
            SuggestionChip(
                onClick = { /* Do something! */ },
                label = { Text("Suggestion Chip") }
            )
            Spacer(modifier = Modifier.height(dimenB3))

            TitleTextView(
                "Horizontally Scrollable Chips", modifier = Modifier.padding(bottom = dimenB3)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    repeat(10) { index ->
                        AssistChip(
                            modifier = Modifier.padding(horizontal = dimenB1),
                            onClick = { /* do something*/ },
                            label = { Text("Chip $index") }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimenB3))

            TitleTextView(
                "Multi-line Chips", modifier = Modifier.padding(bottom = dimenB3)
            )
            Column {
                FlowRow(
                    Modifier
                        .fillMaxWidth(1f)
                        .wrapContentHeight(align = Alignment.Top),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    repeat(10) { index ->
                        AssistChip(
                            modifier = Modifier
                                .padding(horizontal = dimenB1)
                                .align(alignment = Alignment.CenterVertically),
                            onClick = { /* do something*/ },
                            label = { Text("Chip $index") }
                        )
                    }
                }
            }

            CommentTextView(
                text = "When using the Chips from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
                modifier = Modifier.padding(top = dimenB3)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimenB5),
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
        Text(
            text = "Code example",
            modifier = Modifier.padding(start = dimenB4, top = dimenB3, bottom = dimenB3),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall
        )
        CodeSnippet(codeText = Chip.CODE_SNIPPET)
        Spacer(modifier = Modifier.height(dimenB5))
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ChipScreenPreview() {
    MagentaA11yThemePreview {
        ChipScreen(setTopBar = {})
    }
}