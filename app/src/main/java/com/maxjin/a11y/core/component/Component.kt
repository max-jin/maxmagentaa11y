/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.core.component

import com.maxjin.a11y.ui.nav.NavDestination

/**
 * App UI component
 */
data class Component(
    val name: String,
    // Used for searching and displaying
    val nameOptions: List<String>? = null,
    val available: Boolean = true,
    val type: ComponentType = ComponentType.CONTROL,
    val navDestination: NavDestination
) {
    companion object {
        val allComponents: List<Component>
            get() = listOf(
                Component("Button", navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Captcha", available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Carousel", available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("CheckBox", navDestination = NavDestination.COMPONENT_CHECKBOX),
                Component("Drawer / Snappable / Sheet", listOf("Menu", "Bottom Sheet", "BottomSheet"), available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Link", navDestination = NavDestination.COMPONENT_LINK),
                Component("Menu", listOf("Drawer", "DropdownMenu", "DropdownTextBox", "DropdownMenuBox"), available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Pagination control", available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Picker / Spinner / Dropdown", available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Radio button", listOf("RadioButton"), navDestination = NavDestination.COMPONENT_RADIO_BUTTON),
                Component("Range slider", available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Segmented Control / Tab", available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Stepper", available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Table row button", listOf("LazyColumn"), available = false, navDestination = NavDestination.COMPONENT_BUTTON),
                Component("Text input", listOf("TextField", "Text fields"), navDestination = NavDestination.COMPONENT_TEXT_FIELD),
                Component("Toggle switch", listOf("Switch"), navDestination = NavDestination.COMPONENT_SWITCH),
                Component("Alert Dialog", listOf("Alert"), available = true, type = ComponentType.NOTIFICATION, navDestination = NavDestination.COMPONENT_ALERT_DIALOG),
                Component("Modal", listOf("Bottom sheet"), available = false, type = ComponentType.NOTIFICATION, navDestination = NavDestination.COMPONENT_ALERT_DIALOG),
                Component("Toast snackbar banner", available = false, type = ComponentType.NOTIFICATION, navDestination = NavDestination.COMPONENT_BUTTON)
            )
    }
}

enum class ComponentType {
    CONTROL,
    NOTIFICATION
}
