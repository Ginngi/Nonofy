package com.nonofy.game.presentation.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nonofy.ui.theme.BlackPaperTransparency
import com.nonofy.ui.theme.WhitePaperTransparency60

@Composable
fun HorizontalBoardHeader(
    header: String,
    modifier: Modifier = Modifier,
    isDarkThemeEnabled: Boolean = isSystemInDarkTheme()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(if (isDarkThemeEnabled) WhitePaperTransparency60 else BlackPaperTransparency)
            .clip(RoundedCornerShape(2.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        val headerList = header.split(',')
        for (item in headerList) {
            Text(
                text = item,
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .padding(horizontal = 1.dp),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    HorizontalBoardHeader("1,1,2,10")
}