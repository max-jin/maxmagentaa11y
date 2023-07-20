/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.core.component

/**
 * App UI component
 */
data class Component(
    val name: String,
    // Used for searching and displaying
    val nameOptions: List<String>? = null,
    val available: Boolean = true,
    val type: ComponentType = ComponentType.CONTROL
) {
    companion object {
        val allComponents: List<Component>
            get() = listOf(
                Component("Button"),
                Component("Captcha", available = false),
                Component("Carousel", available = false),
                Component("CheckBox"),
                Component("Drawer / Snappable / Sheet", listOf("Menu", "Bottom Sheet", "BottomSheet"), available = false),
                Component("Link"),
                Component("Menu", listOf("Drawer", "DropdownMenu", "DropdownTextBox", "DropdownMenuBox"), available = false),
                Component("Pagination control", available = false),
                Component("Picker / Spinner / Dropdown", available = false),
                Component("Radio button", listOf("RadioButton")),
                Component("Range slider", available = false),
                Component("Segmented Control / Tab", available = false),
                Component("Stepper", available = false),
                Component("Table row button", listOf("LazyColumn"), available = false),
                Component("Text input", listOf("TextField", "Text fields")),
                Component("Toggle switch", listOf("Switch")),
                Component("Alert Dialog", listOf("Alert"), available = true, type = ComponentType.NOTIFICATION),
                Component("Modal", listOf("Bottom sheet"), available = false, type = ComponentType.NOTIFICATION),
                Component("Toast snackbar banner", available = false, type = ComponentType.NOTIFICATION)
            )
    }
}

enum class ComponentType {
    CONTROL,
    NOTIFICATION
}
