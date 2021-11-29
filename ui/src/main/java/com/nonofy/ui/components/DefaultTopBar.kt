package com.nonofy.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTopBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        elevation = 4.dp
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    DefaultTopBar("Hello World")
}