/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object Slider {

    const val CODE_SNIPPET = "var sliderPosition by remember { mutableStateOf(0f) }\n" +
        "Column {\n" +
        "    Slider(\n" +
        "        modifier = Modifier.semantics { contentDescription = \"Regular Slider Description\" },\n" +
        "        value = sliderPosition,\n" +
        "        onValueChange = { sliderPosition = it })\n" +
        "    Text(\n" +
        "        text = \"\${(sliderPosition / 0.01f).toInt()} %\",\n" +
        "        modifier = Modifier.align(Alignment.End),\n" +
        "        textAlign = TextAlign.Start,\n" +
        "        color = MaterialTheme.colorScheme.onBackground,\n" +
        "        style = MaterialTheme.typography.titleSmall\n" +
        "    )\n" +
        "}"

}