package com.example.myfitnesshub.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfitnesshub.navigation.Screen
import com.example.myfitnesshub.ui.screens.*
import com.example.myfitnesshub.R
import androidx.navigation.NavController
import com.example.myfitnesshub.viewmodel.WorkoutViewModel


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: WorkoutViewModel = viewModel()
    //Routes should not show the bottom bar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    //list of routes that should be full screen
    val fullScreenRoutes = listOf(
        "create_plan_step1",
        "create_plan_step2/{days}",
        "create_plan_step3/{days}/{weeks}"
    )
    // Check if we should do full screen
    val isFullScreen = currentRoute in fullScreenRoutes

    // 1. Define the items list here!
    val items = listOf(
        Screen.Home,
        Screen.Workouts,
        Screen.Nutrition,
        Screen.Health
    )

    Scaffold(
        bottomBar = {
            if (!isFullScreen) {
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
                                    else -> painterResource(id = R.drawable.home_tab_logo)
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
        }

    ) { innerPadding ->
        // This is the "window" that swaps screens
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(if (isFullScreen) PaddingValues(0.dp) else innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Workouts.route) { WorkoutScreen(navController) }
            composable(Screen.Nutrition.route) { NutritionScreen() }
            composable(Screen.Health.route) { HealthScreen() }

            composable(
                route = "create_plan_step1",
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
                popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
            ) {
                CreatePlanStep1Screen(navController)
            }

            composable(
                route = "create_plan_step2/{days}",
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
            ) { backStackEntry ->
                val days = backStackEntry.arguments?.getString("days")?.toInt() ?: 3


                CreatePlanStep2Screen(navController, days)
            }

            composable(
                route = "create_plan_step3/{days}/{weeks}", // Match this exactly
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
            ) { backStackEntry ->
                val days = backStackEntry.arguments?.getString("days")?.toInt() ?: 3
                val weeks = backStackEntry.arguments?.getString("weeks")?.toInt() ?: 4

                // Title is not in the route anymore because we will type it in Step 3
                CreatePlanStep3Screen(navController, viewModel, days, weeks)
            }
        }
    }
}