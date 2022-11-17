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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.headers.LineState
import com.nonofy.game.impl.domain.models.Difficulty

@Composable
fun HorizontalBoardHeader(
    header: HeaderState,
    difficulty: Difficulty,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
        modifier = modifier.background(Color.Transparent)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(getAnimatedBackground(isCompleted = header.isCompleted).value)
                .padding(horizontal = 4.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            for (item in header.lines) {
                Text(
                    text = item.numberPixels.toString(),
                    color = getHeaderTextColor(
                        isHeaderCompleted = header.isCompleted,
                        isLineCompleted = item.isCompleted
                    ),
                    modifier = Modifier.padding(horizontal = 0.5.dp),
                    style = if (item.isCompleted) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle(),
                    fontSize = getHeaderTextSizeFromDifficulty(difficulty),
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    Column {
        HorizontalBoardHeader(HeaderState.empty(), Difficulty.MEDIUM)
        HorizontalBoardHeader(HeaderState.empty(isCompleted = true), Difficulty.MEDIUM)
        HorizontalBoardHeader(
            HeaderState.empty(
                lines = listOf(
                    LineState.empty(
                        2,
                        isCompleted = true
                    ), LineState.empty(1)
                )
            ), Difficulty.MEDIUM
        )
        HorizontalBoardHeader(
            HeaderState.empty(
                isCompleted = true,
                lines = listOf(LineState.empty(2, isCompleted = true), LineState.empty(1))
            ), Difficulty.MEDIUM
        )
    }
}