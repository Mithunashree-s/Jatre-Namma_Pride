package com.example.jatrenammapride.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColors = lightColorScheme(

    primary = PrimaryRed,

    secondary = FestivalOrange,

    background = LightCream
)

@Composable
fun JatreNammaPrideTheme(

    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme = AppColors,

        content = content
    )
}