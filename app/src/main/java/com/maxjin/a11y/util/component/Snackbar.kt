/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object Snackbar {

    const val SNACKBAR_CODE_SNIPPET = "snackbarHost = {\n" +
        "    SnackbarHost(snackbarHostState) { data ->\n" +
        "        // custom snackbar with the custom action button\n" +
        "        Snackbar(\n" +
        "            modifier = Modifier.padding(12.dp),\n" +
        "            action = data.visuals.actionLabel?.let {\n" +
        "                { TextButton(onClick = { data.dismiss() }) { Text(it, color = MaterialTheme.colorScheme.background) } }\n" +
        "            },\n" +
        "            dismissAction = if (data.visuals.withDismissAction) {\n" +
        "                { IconButton(onClick = { data.dismiss() }) { Icon(Icons.Filled.Close, contentDescription = \"dismiss\") } }\n" +
        "            } else null\n" +
        "        ) {\n" +
        "            Text(data.visuals.message)\n" +
        "        }\n" +
        "    }\n" +
        "}"
}