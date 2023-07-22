/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object Button {

    const val CODE_SNIPPET = "Box(\n" +
        "    modifier = Modifier\n" +
        "        .fillMaxWidth()\n" +
        "        .height(IntrinsicSize.Min)\n" +
        "        .clip(RoundedCornerShape(dimenB3))\n" +
        "        .clickable(onClick = {}, role = Role.Button)\n" +
        "        .background(color = MaterialTheme.colorScheme.primary)\n" +
        ") {\n" +
        "    Row(\n" +
        "        modifier = Modifier\n" +
        "            .padding(top = dimenB3, bottom = dimenB3, start = dimenB4)\n" +
        "            .align(Alignment.CenterStart)\n" +
        "    ) {\n" +
        "        Icon(Icons.Filled.Add, contentDescription = \"thumb up\", tint = MaterialTheme.colorScheme.background)\n" +
        "        Text(\n" +
        "            text = \"Button\",\n" +
        "            textAlign = TextAlign.Start,\n" +
        "            modifier = Modifier.padding(start = dimenB3),\n" +
        "            style = MaterialTheme.typography.titleMedium,\n" +
        "            color = MaterialTheme.colorScheme.background\n" +
        "        )\n" +
        "    }\n" +
        "    Icon(\n" +
        "        modifier = Modifier\n" +
        "            .align(Alignment.CenterEnd)\n" +
        "            .padding(end = dimenB4),\n" +
        "        imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.background\n" +
        "    )\n" +
        "}"

}