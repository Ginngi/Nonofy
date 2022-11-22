package com.nonofy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nonofy.ui.R

val spaceMono = FontFamily(
    Font(R.font.space_mono_regular, weight = FontWeight.Normal),
    Font(R.font.space_mono_bold, weight = FontWeight.Bold),
    Font(R.font.space_mono_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.space_mono_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
)

// Set of Material typography styles to start with
val SpaceMono = Typography(
    body1 = TextStyle(
        fontFamily = spaceMono,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = spaceMono,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    button = TextStyle(
        fontFamily = spaceMono,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
)