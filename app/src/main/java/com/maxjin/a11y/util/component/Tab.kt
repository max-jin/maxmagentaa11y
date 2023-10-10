/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object Tab {

    const val CODE_SNIPPET = "var tabIndex by remember { mutableStateOf(0) }\n" +
        "val titles = listOf(\"Tab 1\", \"Tab 2\", \"Tab 3\")\n" +
        "Column(modifier = Modifier.clip(RoundedCornerShape(dimenB3))) {\n" +
        "    TabRow(selectedTabIndex = tabIndex) {\n" +
        "        titles.forEachIndexed { index, title ->\n" +
        "            Tab(\n" +
        "                selected = tabIndex == index,\n" +
        "                onClick = { tabIndex = index },\n" +
        "                text = { Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis) }\n" +
        "            )\n" +
        "        }\n" +
        "    }\n" +
        "    Box(modifier = Modifier\n" +
        "        .fillMaxWidth()\n" +
        "        .height(200.dp)\n" +
        "        .background(MaterialTheme.colorScheme.background)) {\n" +
        "        Text(\n" +
        "            modifier = Modifier.align(Alignment.Center),\n" +
        "            text = \"Tab \${tabIndex + 1} selected\",\n" +
        "            style = MaterialTheme.typography.titleMedium,\n" +
        "            color = MaterialTheme.colorScheme.primary\n" +
        "        )\n" +
        "    }\n" +
        "}"

}