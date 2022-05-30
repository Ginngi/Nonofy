package com.nonofy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nonofy.ui.R

val noto = FontFamily(
    Font(R.font.noto_nans_jp_regular, weight = FontWeight.Normal),
    Font(R.font.noto_nans_jp_bold, weight = FontWeight.Bold),
    Font(R.font.noto_nans_jp_black, weight = FontWeight.Black),
    Font(R.font.noto_nans_jp_medium, weight = FontWeight.Medium),
    Font(R.font.noto_nans_jp_thin, weight = FontWeight.Thin),
    Font(R.font.noto_nans_jp_light, weight = FontWeight.Light),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = noto,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = noto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = noto,
        fontWeight = FontWeight.Black,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = noto,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        fontFamily = noto,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        fontFamily = noto,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    )
)