package com.nonofy.game.impl.presentation.components.headers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.impl.domain.models.Difficulty

@Composable
fun VerticalBoardHeader(
    header: HeaderState, difficulty: Difficulty, modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .background(if (header.isCompleted) MaterialTheme.colors.background else MaterialTheme.colors.primary)
                .padding(horizontal = 2.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            val headerList = header.value.split(',')

            for (item in headerList) {
                Text(
                    text = item,
                    color = if (header.isCompleted) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    fontSize = getHeaderTextSizeFromDifficulty(difficulty)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    Row {
        VerticalBoardHeader(HeaderState.empty(), Difficulty.MEDIUM)
        VerticalBoardHeader(HeaderState.empty(isCompleted = true), Difficulty.MEDIUM)
    }
}