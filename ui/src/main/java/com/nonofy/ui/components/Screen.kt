package com.nonofy.ui.components

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nonofy.ui.theme.NonofyTheme

@Composable
fun Screen(topBarTitle: String, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    NonofyTheme {
        Scaffold(topBar = { DefaultTopBar(topBarTitle) }, modifier = modifier) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Screen(topBarTitle = "Hello World") {
        Text(text = "Hello World")
    }
}