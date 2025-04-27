package com.example.weatherly.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BaseText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = color,
        fontFamily = FontFamily.Serif
    )
}