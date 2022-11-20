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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.headers.LineState
import com.nonofy.game.impl.domain.models.Difficulty

@Composable
fun VerticalBoardHeader(
    header: HeaderState, difficulty: Difficulty, modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        modifier = modifier.background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .background(getAnimatedBackground(isCompleted = header.isCompleted).value)
                .padding(top = 8.dp, bottom = 4.dp, start = 2.dp, end = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            for (item in header.lines) {
                Text(
                    text = item.numberPixels.toString(),
                    color = getHeaderTextColor(
                        isHeaderCompleted = header.isCompleted,
                        isLineCompleted = item.isCompleted
                    ),
                    style = MaterialTheme.typography.body1.copy(
                        textDecoration = if (item.isCompleted) {
                            TextDecoration.LineThrough
                        } else TextDecoration.None
                    ),
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
        VerticalBoardHeader(
            HeaderState.empty(
                lines = listOf(
                    LineState.empty(
                        2,
                        isCompleted = true
                    ), LineState.empty(1)
                )
            ), Difficulty.MEDIUM
        )
        VerticalBoardHeader(
            HeaderState.empty(
                isCompleted = true,
                lines = listOf(LineState.empty(2, isCompleted = true), LineState.empty(1))
            ), Difficulty.MEDIUM
        )
    }
}