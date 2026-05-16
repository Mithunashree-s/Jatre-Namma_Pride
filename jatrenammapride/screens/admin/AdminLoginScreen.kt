package com.example.jatrenammapride.screens.admin

import android.widget.Toast
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AdminLoginScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(20.dp),

        verticalArrangement = Arrangement.Top,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CenterAlignedTopAppBar(

            title = {
                Text("Admin Login")
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

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Admin Login",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB71C1C)
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(

                    value = email,

                    onValueChange = {
                        email = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Admin Email")
                    },

                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(

                    value = password,

                    onValueChange = {
                        password = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Password")
                    },

                    visualTransformation =
                        PasswordVisualTransformation(),

                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(

                    onClick = {

                        if (
                            email.isEmpty() ||
                            password.isEmpty()
                        ) {

                            Toast.makeText(
                                context,
                                "Enter all fields",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@Button
                        }

                        auth.signInWithEmailAndPassword(
                            email,
                            password
                        )

                            .addOnSuccessListener {

                                Toast.makeText(
                                    context,
                                    "Login Successful",
                                    Toast.LENGTH_SHORT
                                ).show()

                                navController.navigate(
                                    "admin_panel"
                                )
                            }

                            .addOnFailureListener {

                                Toast.makeText(
                                    context,
                                    "Invalid Admin Credentials",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                    },

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(14.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD84315)
                    )
                ) {

                    Text(
                        text = "LOGIN",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}