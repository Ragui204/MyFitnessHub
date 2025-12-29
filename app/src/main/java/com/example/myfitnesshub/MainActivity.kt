package com.example.myfitnesshub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import com.example.myfitnesshub.ui.MainScreen
import com.example.myfitnesshub.ui.MyFitnessHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyFitnessHubTheme {
                // List of all permissions required for Home, Nutrition, and Workouts
                val permissionsToRequest = arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.ACTIVITY_RECOGNITION,
                    android.Manifest.permission.POST_NOTIFICATIONS
                )

                // The launcher that pops up when the app opens
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions()
                ) { /* results are handled by the system */ }

                // Automatically trigger the popup on startup
                LaunchedEffect(Unit) {
                    launcher.launch(permissionsToRequest)
                }

                MainScreen()
            }
        }
    }
}