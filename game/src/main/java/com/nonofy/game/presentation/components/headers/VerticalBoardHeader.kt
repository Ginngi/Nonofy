package com.nonofy.game.presentation.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.nonofy.ui.theme.BlackPaperTransparency
import com.nonofy.ui.theme.WhitePaperTransparency60

@Composable
fun VerticalBoardHeader(header: String, modifier: Modifier = Modifier, isDarkThemeEnabled: Boolean = isSystemInDarkTheme()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(if (isDarkThemeEnabled) WhitePaperTransparency60 else BlackPaperTransparency)
            .padding(vertical = 2.dp)
            .clip(RoundedCornerShape(2.dp)),
        verticalArrangement = Arrangement.Bottom
    ) {
        val headerList = header.split(',')

        for (item in headerList) {
            Text(
                text = item,
                fontSize = 6.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    VerticalBoardHeader("1,1,2,10")
}