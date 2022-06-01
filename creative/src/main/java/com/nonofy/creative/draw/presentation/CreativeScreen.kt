package com.nonofy.creative.draw.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nonofy.creative.draw.domain.CreativeEvent
import com.nonofy.ui.components.Screen
import com.nonofy.ui.components.grid.Grid
import com.nonofy.ui.components.grid.GridState

@Composable
fun CreativeScreen() {
    CreativeScreen(creativeViewModel = hiltViewModel())
}

@Composable
private fun CreativeScreen(creativeViewModel: CreativeViewModel) {
    val state by remember(creativeViewModel) { creativeViewModel.state }.collectAsState()

    CreativeScreenContent(state) { creativeViewModel.event(it) }
}

@Composable
private fun CreativeScreenContent(
    state: CreativeScreenState, event: (CreativeEvent) -> Unit
) {
    Screen {
        Grid(state = state.gridState, onPixelClicked = {})
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    CreativeScreenContent(CreativeScreenState(GridState.empty(10)), {})
}