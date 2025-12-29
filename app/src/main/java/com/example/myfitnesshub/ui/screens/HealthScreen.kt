package com.example.myfitnesshub.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.health.connect.client.HealthConnectClient
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfitnesshub.viewmodel.HealthViewModel


@Composable
fun HealthScreen(viewModel: HealthViewModel = viewModel()) {
    val context = LocalContext.current
    val isGranted by viewModel.permissionGranted.collectAsState()
    val sdkStatus by viewModel.healthConnectStatus.collectAsState()

    // Keep checking status so the UI updates if the user grants permissions at startup
    LaunchedEffect(Unit) {
        viewModel.checkHealthConnectAvailability(context)
        viewModel.checkCurrentPermissions(context)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (sdkStatus) {
            HealthConnectClient.SDK_AVAILABLE -> {
                if (isGranted) {
                    Text("✅ All Systems Go! Connected to Health Data.")
                } else {
                    // This shows only if they denied permissions at startup
                    Text("Permissions are missing. Please enable them in Settings.")
                }
            }
            HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED -> {
                Text("Health Connect is required for Samsung Watch sync.")
                Button(onClick = { /* Play Store Link code */ }) {
                    Text("Install Health Connect")
                }
            }
            else -> Text("Health Connect is not supported.")
        }
    }
}