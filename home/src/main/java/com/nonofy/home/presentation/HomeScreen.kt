package com.nonofy.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nonofy.ui.components.Screen

@Composable
fun HomeScreen() {
    HomeScreen(homeViewModel = hiltViewModel())
}

@Composable
private fun HomeScreen(homeViewModel: HomeViewModel) {
    HomeScreenContent { homeViewModel.onEvent(it) }
}

@Composable
private fun HomeScreenContent(event: (HomeEvent) -> Unit) {
    Screen {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight()
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                enabled = false
            ) {
                Text(text = "Continue (Coming soon)")
            }

            Button(
                onClick = { event(HomeEvent.ClickedEasyLevel) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(text = "Challenge Mode")
            }

            Button(
                onClick = { event(HomeEvent.ClickedCreativeMode) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(text = "Creative Mode")
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    HomeScreen()
}