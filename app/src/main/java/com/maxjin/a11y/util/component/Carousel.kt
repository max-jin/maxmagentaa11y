/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object Carousel {

    const val CODE_SNIPPET = "val horizontalPagerState = rememberPagerState()\n" +
        "HorizontalPager(\n" +
        "    state = horizontalPagerState,\n" +
        "    modifier = Modifier\n" +
        "        .fillMaxWidth(),\n" +
        "    pageCount = 5,\n" +
        "    contentPadding = PaddingValues(horizontal = dimenB7)\n" +
        ") { pageIndex ->\n" +
        "    Box(\n" +
        "        modifier = Modifier\n" +
        "            .clip(RoundedCornerShape(dimenB5))\n" +
        "            .fillMaxWidth()\n" +
        "            .aspectRatio(1f)\n" +
        "            .semantics(mergeDescendants = true) {},\n" +
        "        contentAlignment = Alignment.Center\n" +
        "    ) {\n" +
        "        Box(modifier = Modifier) {\n" +
        "            Text(text = (pageIndex + 1).toString(), style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onBackground)\n" +
        "        }\n" +
        "    }\n" +
        "}"

}