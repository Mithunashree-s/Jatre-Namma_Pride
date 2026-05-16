package com.example.jatrenammapride.screens.home

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
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF3E0),
                        Color(0xFFFFE0B2)
                    )
                )
            )
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Icon(
            imageVector = Icons.Default.Celebration,
            contentDescription = "Festival",
            tint = Color(0xFFD84315),
            modifier = Modifier.size(90.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Jatre Namma Pride",
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFB71C1C)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Celebrate Karnataka Culture",
            fontSize = 18.sp,
            color = Color(0xFF5D4037)
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp)),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Festival Highlights",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD84315)
                )

                Spacer(modifier = Modifier.height(20.dp))

                FeatureRow(
                    icon = Icons.Default.Event,
                    title = "Live Event Schedules"
                )

                Spacer(modifier = Modifier.height(16.dp))

                FeatureRow(
                    icon = Icons.Default.LocationOn,
                    title = "Interactive Festival Map"
                )

                Spacer(modifier = Modifier.height(16.dp))

                FeatureRow(
                    icon = Icons.Default.Security,
                    title = "Emergency Safety Support"
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD84315)
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Experience the Tradition",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Explore Karnataka's rich heritage, folk arts, music, dance, and festive spirit all in one place.",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun FeatureRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFFB71C1C),
            modifier = Modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}