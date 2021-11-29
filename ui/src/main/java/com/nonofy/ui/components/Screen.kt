package com.nonofy.ui.components

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nonofy.ui.theme.PokedixTheme

@Composable
fun Screen(topBarTitle: String, content: @Composable () -> Unit) {
    PokedixTheme {
        Scaffold(topBar = { DefaultTopBar(topBarTitle) }) {
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