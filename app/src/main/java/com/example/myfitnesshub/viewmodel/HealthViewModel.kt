package com.example.myfitnesshub.viewmodel

import androidx.health.connect.client.HealthConnectClient
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HealthViewModel : ViewModel() {

    //Tracks if permissions are granted for UI to react
    private val _permissionsGranted = MutableStateFlow(false)
    val permissionGranted: StateFlow<Boolean> = _permissionsGranted

    //List of permissions
    val requiredPermissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACTIVITY_RECOGNITION,
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    fun checkCurrentPermissions(context: android.content.Context) {
        val allGranted = requiredPermissions.all { permission ->
            androidx.core.content.ContextCompat.checkSelfPermission(
                context, permission
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        }
        _permissionsGranted.value = allGranted
    }
    fun onPermissionsResult(isGranted: Boolean) {
        _permissionsGranted.value = isGranted
    }

    private  val _healthConnectStatus = MutableStateFlow(HealthConnectClient.SDK_UNAVAILABLE)
    val healthConnectStatus: StateFlow<Int> = _healthConnectStatus

    //Cheeck if Health Connect is installed or needs an update
    fun checkHealthConnectAvailability(context: android.content.Context) {
        _healthConnectStatus.value = HealthConnectClient.getSdkStatus(context)
    }
}