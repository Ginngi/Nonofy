package com.nonofy.game.presentation.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.ui.theme.Purple700

@Composable
fun HorizontalBoardHeader(header: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Purple700)
            .padding(4.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        for (item in header) {
            Text(
                text = item.toString(), modifier = Modifier
                    .padding(end = 4.dp)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    HorizontalBoardHeader("112")
}