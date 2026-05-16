package com.example.jatrenammapride.screens.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MapScreen(
    navController: NavController
) {

    val festivalLocation = LatLng(
        12.9716,
        77.5946
    )

    val cameraPositionState =
        rememberCameraPositionState {

            position = CameraPosition.fromLatLngZoom(
                festivalLocation,
                14f
            )
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
    ) {

        CenterAlignedTopAppBar(

            title = {
                Text("Festival Map")
            },

            navigationIcon = {

                IconButton(

                    onClick = {
                        navController.popBackStack()
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        GoogleMap(

            modifier = Modifier.fillMaxSize(),

            cameraPositionState = cameraPositionState
        ) {

            Marker(

                state = MarkerState(
                    position = festivalLocation
                ),

                title = "Festival Location"
            )
        }
    }
}