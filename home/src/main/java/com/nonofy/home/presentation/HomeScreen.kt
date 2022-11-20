package com.nonofy.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nonofy.home.R
import com.nonofy.ui.components.Screen

@Composable
fun HomeScreen() {
    HomeScreen(homeViewModel = hiltViewModel())
}

@Composable
private fun HomeScreen(homeViewModel: HomeViewModel) {
    val hasGameSaved by remember(homeViewModel) { homeViewModel.hasSavedGame }
        .collectAsState()

    HomeScreenContent(hasGameSaved) { homeViewModel.onEvent(it) }
}

@Composable
private fun HomeScreenContent(
    hasGameSaved: Boolean,
    event: (HomeEvent) -> Unit
) {
    Screen {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight()
        ) {
            if (hasGameSaved) {
                Button(
                    onClick = { event(HomeEvent.ClickedContinueGame) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                ) {
                    Text(
                        color = MaterialTheme.colors.background,
                        text = stringResource(R.string.continue_game),
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }

            Button(
                onClick = { event(HomeEvent.ClickedEasyLevel) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        color = MaterialTheme.colors.background,
                        text = stringResource(R.string.challenge),
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        color = MaterialTheme.colors.background,
                        text = stringResource(R.string.mode),
                        style = MaterialTheme.typography.h1
                    )
                }
            }

            Button(
                onClick = { event(HomeEvent.ClickedCreativeMode) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        color = MaterialTheme.colors.background,
                        text = stringResource(R.string.creative),
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        color = MaterialTheme.colors.background,
                        text = stringResource(R.string.mode),
                        style = MaterialTheme.typography.h1
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    HomeScreenContent(false) {}
}