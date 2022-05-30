package com.nonofy.game.presentation.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.domain.models.Difficulty

@Composable
fun VerticalBoardHeader(
    header: String,
    difficulty: Difficulty,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(vertical = 2.dp)
            .clip(RoundedCornerShape(2.dp)),
        verticalArrangement = Arrangement.Bottom
    ) {
        val headerList = header.split(',')

        for (item in headerList) {
            Text(
                text = item,
                fontSize = getHeaderTextSizeFromDifficulty(difficulty)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    VerticalBoardHeader("1,1,2,10", Difficulty.MEDIUM)
}