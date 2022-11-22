package com.nonofy.creative.draw.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CreativeBoardHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            color = MaterialTheme.colors.primary,
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
    }
}