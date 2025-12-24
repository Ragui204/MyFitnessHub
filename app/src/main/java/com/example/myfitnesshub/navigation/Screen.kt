package com.example.myfitnesshub.navigation

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Workouts : Screen("workouts", "Workouts")
    object Nutrition : Screen("nutrition", "Nutrition")
    object Health : Screen("health", "Health")
}