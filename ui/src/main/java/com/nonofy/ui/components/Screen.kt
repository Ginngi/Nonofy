package com.nonofy.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(modifier = modifier) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Screen {
        Text(text = "Hello World")
    }
}