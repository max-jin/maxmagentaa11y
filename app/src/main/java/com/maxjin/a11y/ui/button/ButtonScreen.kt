/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlin.random.Random

@Composable
fun ButtonScreen() {
    Text(text = "Welcome to Button page ${Random.nextInt()}")
}