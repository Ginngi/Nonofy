package com.nonofy.game.presentation.components.headers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nonofy.game.domain.models.Difficulty

@Composable
fun HorizontalBoardHeader(
    header: HeaderState,
    difficulty: Difficulty,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(2.dp)),
        horizontalArrangement = Arrangement.End,
    ) {
        val headerList = header.value.split(',')
        for (item in headerList) {
            Text(
                text = item,
                modifier = Modifier
                    .padding(horizontal = 0.5.dp),
                fontSize = getHeaderTextSizeFromDifficulty(difficulty),
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    HorizontalBoardHeader(HeaderState.empty(), Difficulty.MEDIUM)
}