/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.util.component

object Sheet {

    const val SHEET_CODE_SNIPPET = "var openBottomSheet by rememberSaveable { mutableStateOf(false) }\n" +
        "val scope = rememberCoroutineScope()\n" +
        "val bottomSheetState = rememberModalBottomSheetState()\n" +
        "if (openBottomSheet) {\n" +
        "    ModalBottomSheet(\n" +
        "        onDismissRequest = { openBottomSheet = false },\n" +
        "        sheetState = bottomSheetState\n" +
        "    ) {\n" +
        "        Column(modifier = Modifier.fillMaxWidth()) {\n" +
        "            OutlinedButton(\n" +
        "                modifier = Modifier.align(Alignment.CenterHorizontally),\n" +
        "                // Note: If you provide logic outside of onDismissRequest to remove the sheet,\n" +
        "                // you must additionally handle intended state cleanup, if any.\n" +
        "                onClick = {\n" +
        "                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {\n" +
        "                        if (!bottomSheetState.isVisible) {\n" +
        "                            openBottomSheet = false\n" +
        "                        }\n" +
        "                    }\n" +
        "                }\n" +
        "            ) {\n" +
        "                Text(\"Hide Bottom Sheet\")\n" +
        "            }\n" +
        "            LazyColumn {\n" +
        "                items(50) {\n" +
        "                    ListItem(\n" +
        "                        headlineContent = { Text(\"Sheet item \$it\") },\n" +
        "                        leadingContent = {\n" +
        "                            Icon(\n" +
        "                                Icons.Default.Star,\n" +
        "                                contentDescription = \"Localized description\"\n" +
        "                            )\n" +
        "                        }\n" +
        "                    )\n" +
        "                }\n" +
        "            }\n" +
        "        }\n" +
        "    }\n" +
        "}"
}


