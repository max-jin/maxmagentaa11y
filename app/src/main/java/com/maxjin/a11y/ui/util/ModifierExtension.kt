/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

@file:Suppress("unused")

package com.maxjin.a11y.ui.util

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.key.onPreviewKeyEvent

/**
 * Main App background verticalGradient effect
 */
fun Modifier.verticalGradient() = composed {
    this.then(
        background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.tertiaryContainer
                )
            )
        )
    )
}

/**
 * Main App background horizontalGradient effect
 */
fun Modifier.horizontalGradient() = composed {
    this.then(
        background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primaryContainer,
                    MaterialTheme.colorScheme.tertiaryContainer
                )
            )
        )
    )
}

/**
 * To ensure that the checkbox toggle works with keyboard accessibility
 */
fun Modifier.checkboxAccessibilityToggle(
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) = this.then(
    Modifier.onPreviewKeyEvent {
        if ((it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER || it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_SPACE) && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
            onValueChange(!value)
            true
        } else
            false
    }
)