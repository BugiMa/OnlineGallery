package com.bugima.onlinegallery.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bugima.onlinegallery.R

val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_light)),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.fredoka_light)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
)