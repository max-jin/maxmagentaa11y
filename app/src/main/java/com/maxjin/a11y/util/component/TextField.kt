/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object TextField {
    const val CODE_SNIPPET = "var textFieldValue by rememberSaveable { mutableStateOf(\"\") }\n" +
        "TextField(\n" +
        "    value = textFieldValue,\n" +
        "    onValueChange = { textFieldValue = it },\n" +
        "    modifier = Modifier.fillMaxWidth(),\n" +
        "    label = { Text(text = \"Regular TextField\") },\n" +
        "    trailingIcon = {\n" +
        "        if (textFieldValue.isNotEmpty()) Icon(\n" +
        "            Icons.Filled.Clear, contentDescription = \"Clear\", modifier = Modifier.clickable(\n" +
        "                onClick = { textFieldValue = \"\" },\n" +
        "                role = Role.Button\n" +
        "            )\n" +
        "        )\n" +
        "    }\n" +
        ")"
}