package com.example.myfitnesshub.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfitnesshub.navigation.Screen
import com.example.myfitnesshub.ui.screens.*
import com.example.myfitnesshub.R

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // 1. Define the items list here!
    val items = listOf(
        Screen.Home,
        Screen.Workouts,
        Screen.Nutrition,
        Screen.Health
    )

    Scaffold(
        bottomBar = {
            NavigationBar() {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Define your items based on the Screen class
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            // Map each screen to your imported drawable files
                            val iconPainter = when (screen) {
                                Screen.Home -> painterResource(id = R.drawable.home_tab_logo)
                                Screen.Workouts -> painterResource(id = R.drawable.exercise_tab_logo)
                                Screen.Nutrition -> painterResource(id = R.drawable.nutrition_tab_logo)
                                Screen.Health -> painterResource(id = R.drawable.health_tab_logo)
                            }

                            Icon(
                                painter = iconPainter,
                                contentDescription = screen.title,
                                modifier = Modifier.size(24.dp) // Standard size for bottom nav icons
                            )
                        },
                        label = { Text(text = screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // This is the "window" that swaps screens
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Workouts.route) { WorkoutScreen() }
            composable(Screen.Nutrition.route) { NutritionScreen() }
            composable(Screen.Health.route) { HealthScreen() }
        }
    }
}