package com.example.jatrenammapride.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
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
import com.example.jatrenammapride.model.LostFoundItem
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLostFoundScreen(
    navController: NavController
) {

    val firestore = FirebaseFirestore.getInstance()

    val itemList = remember {
        mutableStateListOf<LostFoundItem>()
    }

    LaunchedEffect(Unit) {

        firestore.collection("lost_found")
            .addSnapshotListener { value, _ ->

                itemList.clear()

                value?.documents?.forEach {

                    val item =
                        it.toObject(LostFoundItem::class.java)

                    if (item != null) {
                        itemList.add(item)
                    }
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {

        CenterAlignedTopAppBar(

            title = {
                Text("Manage Lost & Found")
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

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(itemList) { item ->

                Card(
                    modifier = Modifier.padding(vertical = 8.dp),

                    shape = RoundedCornerShape(16.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = item.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(item.description)

                        Spacer(modifier = Modifier.height(10.dp))

                        Checkbox(

                            checked = item.resolved,

                            onCheckedChange = {

                                firestore.collection("lost_found")
                                    .document(item.id)
                                    .update(
                                        "resolved",
                                        it
                                    )
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(

                            onClick = {

                                firestore.collection("lost_found")
                                    .document(item.id)
                                    .delete()
                            },

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {

                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}