/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.slider

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
import com.maxjin.a11y.util.component.Slider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderScreen(modifier: Modifier = Modifier, setTopBar: (AppTopAppBarState) -> Unit) {
    LaunchedEffect(Unit) {
        setTopBar(AppTopAppBarState(title = "Slider", linkAction = LinkAction(url = AppUtil.WebLinks.SLIDER_URL)))
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
                "Regular Slider", modifier = Modifier.padding(bottom = dimenB3)
            )
            var sliderPosition by remember { mutableStateOf(0f) }
            Column {
                Slider(
                    modifier = Modifier.semantics { contentDescription = "Regular Slider Description" },
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it })
                Text(
                    text = "${(sliderPosition / 0.01f).toInt()} %",
                    modifier = Modifier.align(Alignment.End),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            TitleTextView(
                "Custom slider", modifier = Modifier.padding(bottom = dimenB3, top = dimenB3)
            )
            var sliderPosition1 by remember { mutableStateOf(0f) }
            val interactionSource = remember { MutableInteractionSource() }
            val colorsTrack = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.surface, activeTrackColor = MaterialTheme.colorScheme.surface)
            Column {
                Slider(
                    value = sliderPosition1,
                    onValueChange = { sliderPosition1 = it },
                    modifier = Modifier.semantics { contentDescription = "Custom Slider Description" },
                    interactionSource = interactionSource,
                    thumb = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    track = {
                        SliderDefaults.Track(
                            colors = colorsTrack,
                            sliderPositions = SliderPositions()
                        )
                    }
                )
                Text(
                    text = "${(sliderPosition1 / 0.01f).toInt()} %",
                    modifier = Modifier.align(Alignment.End),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            CommentTextView(
                text = "When using the Slider from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
                modifier = Modifier.padding(top = dimenB3)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimenB5),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            TitleTextView(
                "Range slider", modifier = Modifier.padding(bottom = dimenB3, top = dimenB5)
            )
            var sliderPosition2 by remember { mutableStateOf(0f..1f) }
            Column {
                RangeSlider(
                    value = sliderPosition2,
                    onValueChange = { sliderPosition2 = it },
                    modifier = Modifier.semantics { contentDescription = "Localized Description" }
                )
                Text(
                    text = "${(sliderPosition2.start / 0.01f).toInt()} % - ${(sliderPosition2.endInclusive / 0.01f).toInt()} %",
                    modifier = Modifier.align(Alignment.End),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            CommentTextView(
                text = "With the Range slider from native composable, the default behavior will cover the accessibility, no extra actions are needed.",
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
        CodeSnippet(codeText = Slider.CODE_SNIPPET)
        Spacer(modifier = Modifier.height(dimenB5))
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SliderScreenPreview() {
    MagentaA11yTheme {
        SliderScreen(setTopBar = {})
    }
}