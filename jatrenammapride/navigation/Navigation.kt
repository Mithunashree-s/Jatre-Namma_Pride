package com.example.jatrenammapride.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jatrenammapride.screens.admin.AdminLoginScreen
import com.example.jatrenammapride.screens.admin.AdminLostFoundScreen
import com.example.jatrenammapride.screens.admin.AdminPanelScreen
import com.example.jatrenammapride.screens.ai.AiScreen
import com.example.jatrenammapride.screens.home.HomeScreen
import com.example.jatrenammapride.screens.lostfound.LostFoundScreen
import com.example.jatrenammapride.screens.maps.MapScreen
import com.example.jatrenammapride.screens.safety.SafetyScreen
import com.example.jatrenammapride.screens.schedule.ScheduleScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(

                    selected = currentRoute == "home",

                    onClick = {
                        navController.navigate("home")
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },

                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == "schedule",

                    onClick = {
                        navController.navigate("schedule")
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Schedule"
                        )
                    },

                    label = {
                        Text("Schedule")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == "map",

                    onClick = {
                        navController.navigate("map")
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Map"
                        )
                    },

                    label = {
                        Text("Map")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == "lostfound",

                    onClick = {
                        navController.navigate("lostfound")
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "LostFound"
                        )
                    },

                    label = {
                        Text("Lost")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == "safety",

                    onClick = {
                        navController.navigate("safety")
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = "Safety"
                        )
                    },

                    label = {
                        Text("Safety")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == "ai",

                    onClick = {
                        navController.navigate("ai")
                    },

                    icon = {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "AI"
                        )
                    },

                    label = {
                        Text("AI")
                    }
                )

                NavigationBarItem(

                    selected = currentRoute == "admin",

                    onClick = {
                        navController.navigate("admin")
                    },

                    icon = {
                        Icon(
                            imageVector =
                                Icons.Default.AdminPanelSettings,
                            contentDescription = "Admin"
                        )
                    },

                    label = {
                        Text("Admin")
                    }
                )
            }
        }

    ) { paddingValues ->

        NavHost(

            navController = navController,

            startDestination = "home",

            modifier = Modifier.padding(paddingValues)
        ) {

            composable("home") {

                HomeScreen()
            }

            composable("schedule") {

                ScheduleScreen(navController)
            }

            composable("map") {

                MapScreen(navController)
            }

            composable("lostfound") {

                LostFoundScreen(navController)
            }

            composable("safety") {

                SafetyScreen(navController)
            }

            composable("ai") {

                AiScreen(navController)
            }

            composable("admin") {

                AdminLoginScreen(navController)
            }

            composable("admin_panel") {

                AdminPanelScreen(navController)
            }

            composable("admin_lostfound") {

                AdminLostFoundScreen(navController)
            }
        }
    }
}