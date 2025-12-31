package com.example.myfitnesshub.ui.screens

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfitnesshub.viewmodel.WorkoutViewModel


@Composable
fun WorkoutScreen(viewModel: WorkoutViewModel = viewModel()) {
    val selectedTab by viewModel.selectedTabIndex.collectAsState()
    val tabs = viewModel.tabs

    Column(Modifier.fillMaxSize().background(Color(0xFF121212))) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color(0xFF121212),
            contentColor = Color(0xFF00FF9D),
            divider = {}
        ){
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { viewModel.selectedTab(index) },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color(0xFF00FF9D) else Color.Gray,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                )
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        when (selectedTab) {
            0 -> { /* Routines Window */ }
            1 -> { /* Exercises Window */ }
            2 -> { /* Stats Window */ }
        }
    }
}