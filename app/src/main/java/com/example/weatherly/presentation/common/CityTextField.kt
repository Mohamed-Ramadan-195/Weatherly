package com.example.weatherly.presentation.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.SecondaryColor

@Composable
fun CityTextField (
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isClicked = interactionSource.collectIsPressedAsState().value

    LaunchedEffect(key1 = isClicked) { if (isClicked) { onClick?.invoke() } }

    Box {
        OutlinedTextField (
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            placeholder = {
                Text (
                    text = "Enter Your City",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            },
            shape = RoundedCornerShape(Dimen.MediumSpace),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            interactionSource = interactionSource,
            colors = OutlinedTextFieldDefaults.colors (
                unfocusedContainerColor = SecondaryColor,
                focusedContainerColor = SecondaryColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedBorderColor = SecondaryColor,
                focusedBorderColor = SecondaryColor
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CityTextFieldPreview() {
    CityTextField(
        text = "London",
        readOnly = false,
        onValueChange = {},
        onSearch = {}
    )
}