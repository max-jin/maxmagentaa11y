/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object ToggleSwitch {

    const val SWITCH_CODE_SNIPPET = "Box(\n" +
        "    modifier = Modifier\n" +
        "        .fillMaxWidth()\n" +
        "        .clip(RoundedCornerShapeMedium)\n" +
        "        .border(border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary), shape = RoundedCornerShapeMedium)\n" +
        "        .background(color = MaterialTheme.colorScheme.background)\n" +
        "        .toggleable(\n" +
        "            value = onCustomSwitchChecked,\n" +
        "            role = Role.Switch,\n" +
        "            onValueChange = {\n" +
        "                onCustomSwitchChecked = !onCustomSwitchChecked\n" +
        "            }\n" +
        "        )\n" +
        "        .padding(horizontal = dimenB5, vertical = dimenB3)\n" +
        ") {\n" +
        "    Text(\n" +
        "        text = \"Toggle Switch\",\n" +
        "        textAlign = TextAlign.Start,\n" +
        "        modifier = Modifier.align(Alignment.CenterStart),\n" +
        "        style = MaterialTheme.typography.titleMedium,\n" +
        "        color = MaterialTheme.colorScheme.onBackground\n" +
        "    )\n" +
        "    Switch(\n" +
        "        modifier = Modifier.align(Alignment.CenterEnd),\n" +
        "        checked = onCustomSwitchChecked,\n" +
        "        onCheckedChange = null\n" +
        "    )\n" +
        "}"
}