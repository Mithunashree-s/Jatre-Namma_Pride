package com.example.jatrenammapride.screens.lostfound

import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jatrenammapride.model.LostFoundItem
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LostFoundScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val firestore = FirebaseFirestore.getInstance()

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

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
                Text("Lost & Found")
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

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Item Name")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Description")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(

            onClick = {

                val item = LostFoundItem(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = description,
                    resolved = false
                )

                firestore.collection("lost_found")
                    .document(item.id)
                    .set(item)

                Toast.makeText(
                    context,
                    "Submitted",
                    Toast.LENGTH_SHORT
                ).show()

                title = ""
                description = ""
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD84315)
            )
        ) {

            Text(
                text = "Submit",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(itemList) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),

                    shape = RoundedCornerShape(16.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {

                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = item.description
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(

                            text =
                                if (item.resolved)
                                    "Resolved"
                                else
                                    "Pending",

                            color =
                                if (item.resolved)
                                    Color(0xFF2E7D32)
                                else
                                    Color.Red
                        )
                    }
                }
            }
        }
    }
}