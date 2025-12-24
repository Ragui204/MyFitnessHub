package com.example.myfitnesshub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myfitnesshub.ui.MainScreen
import com.example.myfitnesshub.ui.MyFitnessHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Makes the app content go behind the status/navigation bars for a modern look
        enableEdgeToEdge()

        setContent {
            MyFitnessHubTheme {
                // This is where the magic happens: calling your navigation shell
                MainScreen()
            }
        }
    }
}

/* Note: You can safely delete the Greeting() and GreetingPreview()
   functions that were here previously.
*/