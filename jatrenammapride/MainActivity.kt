package com.example.jatrenammapride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jatrenammapride.navigation.AppNavigation
import com.example.jatrenammapride.ui.theme.JatreNammaPrideTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            JatreNammaPrideTheme {

                AppNavigation()
            }
        }
    }
}