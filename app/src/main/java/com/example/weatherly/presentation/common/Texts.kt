package com.example.weatherly.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherly.ui.theme.PrimaryColor

@Composable
fun InfoText (
    modifier: Modifier = Modifier,
    address: String,
    info: String
) {
    Text (
        modifier = modifier,
        text = "$address: $info",
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = PrimaryColor
    )
}