package com.example.jatrenammapride.screens.safety

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SafetyScreen(
    navController: NavController
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CenterAlignedTopAppBar(

            title = {
                Text("Safety & Emergency")
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

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            shape = RoundedCornerShape(18.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp),

                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Button(
                    onClick = {

                        val intent = Intent(Intent.ACTION_DIAL)

                        intent.data = Uri.parse("tel:112")

                        context.startActivity(intent)

                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {

                    Text(
                        text = "Emergency SOS",
                        fontSize = 18.sp
                    )
                }

                Button(
                    onClick = {

                        val sendIntent = Intent().apply {

                            action = Intent.ACTION_SEND

                            putExtra(
                                Intent.EXTRA_TEXT,
                                "My Location: https://maps.google.com"
                            )

                            type = "text/plain"
                        }

                        context.startActivity(
                            Intent.createChooser(
                                sendIntent,
                                "Share Location"
                            )
                        )

                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD84315)
                    )
                ) {

                    Text(
                        text = "Share GPS Location",
                        fontSize = 18.sp
                    )
                }

                Text(
                    text = "Nearby First Aid Centers",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "• Main Entrance Medical Camp"
                )

                Text(
                    text = "• Stage Area First Aid"
                )

                Text(
                    text = "• Parking Zone Emergency Desk"
                )
            }
        }
    }
}