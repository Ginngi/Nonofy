package com.nonofy.home

import androidx.compose.runtime.Composable
import com.nonofy.ui.components.Screen

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Screen(topBarTitle = "Main Activity") {
        Content()
    }
}

@Composable
fun Content() {
}