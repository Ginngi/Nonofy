package com.nonofy.ui.components

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nonofy.ui.theme.OldMauve

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        backgroundColor = OldMauve,
        modifier = modifier,
    ) {
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