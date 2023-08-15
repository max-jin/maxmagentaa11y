/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.util.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxjin.a11y.ui.util.dimenB2
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.ext.copyToClipBoard

@Composable
fun CodeSnippet(
    modifier: Modifier = Modifier,
    codeText: String
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
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
                    onClick = { context.copyToClipBoard(codeText) },
                    role = Role.Button
                )
        )

        Text(
            text = codeText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimenB4),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.primaryContainer,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
