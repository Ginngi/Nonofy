package com.nonofy.game.presentation.components.completedialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nonofy.game.domain.feature.InGameEvent
import com.nonofy.ui.R
import com.nonofy.ui.components.Screen
import com.nonofy.ui.theme.OldMauve
import com.nonofy.ui.theme.RedJapanese

@Composable
fun CompletedSuccessfullyDialog(event: (event: InGameEvent) -> Unit) {
    Dialog(onDismissRequest = {}) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(8.dp)
                .background(OldMauve)
        ) {
            Column(
                modifier = Modifier.padding(all = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Completed!")

                Image(
                    painter = painterResource(id = R.drawable.ic_celebration),
                    contentDescription = "Congratulations",
                    colorFilter = ColorFilter.tint(
                        RedJapanese
                    )
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        event(InGameEvent.ResetBoard)
                    }
                ) {
                    Text("Next level")
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    Screen() {
        CompletedSuccessfullyDialog(event = {})
    }
}