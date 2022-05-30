package com.nonofy.creative

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nonofy.ui.components.Screen

@Composable
fun CreativeScreen() {
    CreativeScreen(creativeViewModel = hiltViewModel())
}

@Composable
private fun CreativeScreen(creativeViewModel: CreativeViewModel) {
    CreativeScreenContent { creativeViewModel.onEvent(it) }
}

@Composable
private fun CreativeScreenContent(event: (CreativeEvent) -> Unit) {
    Screen {
        Text(text = "Creative mode")
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    CreativeScreen()
}