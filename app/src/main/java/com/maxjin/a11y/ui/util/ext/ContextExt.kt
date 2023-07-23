/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.util.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Context.copyToClipBoard(text: String) {
    ContextCompat.getSystemService(this, ClipboardManager::class.java)?.setPrimaryClip(ClipData.newPlainText("", text))
}

fun Context.showToast(text: String, toastLength: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, toastLength).show()
}