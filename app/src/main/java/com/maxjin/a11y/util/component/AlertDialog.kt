/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object AlertDialog {

    const val ALERT_DIALOG_CODE_SNIPPET = "AlertDialog(\n" +
        "    onDismissRequest = {},\n" +
        "    title = {\n" +
        "        Text(text = \"Alert Dialog\")\n" +
        "    },\n" +
        "    text = {\n" +
        "        Text(text = \"Display the alert message here.\")\n" +
        "    },\n" +
        "    confirmButton = {\n" +
        "        Button(onClick = { onAlertDialogOpen = !onAlertDialogOpen }) { Text(text = \"Ok\") }\n" +
        "    }\n" +
        ")"
}