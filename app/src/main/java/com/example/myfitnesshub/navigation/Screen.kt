package com.example.myfitnesshub.navigation

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Workouts : Screen("workouts", "Workouts")
    object CreatePlanStep1 : Screen("create_plan_step1", "Choose Days Per Week")
    object CreatePlanStep2 : Screen("create_plan_step2/{days}", "Duration") {
        fun createRoute(days: Int) = "create_plan_step2/$days"
    }
    object Nutrition : Screen("nutrition", "Nutrition")
    object Health : Screen("health", "Health")
}