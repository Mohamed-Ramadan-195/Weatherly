package com.example.weatherly.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherly.data.dto.Condition
import com.example.weatherly.presentation.utils.Dimen
import com.example.weatherly.ui.theme.PrimaryColor
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

    Card (
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimen.MediumSpace),
        colors = CardDefaults.cardColors (
            contentColor = Color.White,
            containerColor = SecondaryColor,
            disabledContainerColor = SecondaryColor,
            disabledContentColor = Color.White
        )
    ) {
        OutlinedTextField (
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            placeholder = { BaseText("Enter Your City") },
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
fun WeatherIcon(
    modifier: Modifier = Modifier,
    condition: Condition,
    size: Int = 150
) {
    val context = LocalContext.current
    AsyncImage (
        modifier = modifier.size(size.dp),
        model = ImageRequest.Builder(context)
            .data("https:${condition.icon}")
            .crossfade(true).build(),
        contentDescription = "Weather Icon"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastTopAppBar(
    title: String,
    navigateUp: () -> Unit
) {
    TopAppBar (
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        title = {
            BaseText(
                text = title,
                fontSize = 24
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navigateUp() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        }
    )
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(2.dp)
            .background(SecondaryColor)
    )
}

@Composable
fun WeatherImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    contentDescription: String = "Weather Image"
) {
    Image (
        painter = painterResource(image),
        contentDescription = contentDescription,
        modifier = modifier.size(48.dp)
    )
}

@Composable
fun RowContainTextWithIcon (
    imageVector: ImageVector,
    text: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon (
            imageVector = imageVector,
            contentDescription = "icon",
            tint = PrimaryColor
        )
        SpacerWidth(Dimen.ExtraSmallSpace)
        BaseText (
            text = text,
            color = PrimaryColor
        )
    }
}