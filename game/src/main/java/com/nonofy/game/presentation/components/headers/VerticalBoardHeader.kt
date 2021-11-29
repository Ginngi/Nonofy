package com.nonofy.game.presentation.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nonofy.ui.theme.Purple700

@Composable
fun VerticalBoardHeader(header: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Purple700)
            .padding(8.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        for (item in header) {
            Text(
                text = item.toString(),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    VerticalBoardHeader("112")
}