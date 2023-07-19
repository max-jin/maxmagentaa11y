package com.maxjin.a11y.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.RoundedCornerShapeLarge
import com.maxjin.a11y.ui.util.dimenB0
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.dimenB8
import com.maxjin.a11y.ui.util.verticalGradient

// TODO ADD Compose Nav, Component screens - Button, Link, CheckBox, Toggle Switch,
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalGradient()
    ) {
        Spacer(modifier = Modifier.height(dimenB5))
        Text(
            text = "Welcome to \nMagenta A11Y!",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(dimenB4))
        HomeCard(
            listOf("Button", "Link", "Switch", "CheckBox", "RadioButton", "TextField")
        )
    }
}

@Composable
fun HomeCard(buttonList: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimenB0, start = dimenB5, end = dimenB5, bottom = dimenB0)
            .clip(shape = RoundedCornerShapeLarge)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        buttonList.forEach {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimenB8)
                    .clickable(onClick = {}, role = Role.Button),
                verticalArrangement = Arrangement.Center
            ) {
                Text(modifier = Modifier.padding(start = dimenB5), text = it, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GreetingPreview() {
    MagentaA11yTheme {
        HomeScreen()
    }
}