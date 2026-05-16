package com.example.jatrenammapride.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jatrenammapride.model.Event
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ScheduleScreen(
    navController: NavController
) {

    val firestore = FirebaseFirestore.getInstance()

    val eventList = remember {
        mutableStateListOf<Event>()
    }

    LaunchedEffect(Unit) {

        firestore.collection("schedule")
            .addSnapshotListener { value, _ ->

                eventList.clear()

                value?.documents?.forEach {

                    val event =
                        it.toObject(Event::class.java)

                    if (event != null) {

                        eventList.add(event)
                    }
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
    ) {

        CenterAlignedTopAppBar(

            title = {
                Text("Festival Schedule")
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

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            items(eventList) { event ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = event.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Time: ${event.time}"
                        )

                        Text(
                            text = "Location: ${event.location}"
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        if (event.isLive) {

                            Text(
                                text = "LIVE NOW",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}