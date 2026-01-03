package com.example.myfitnesshub.ui.screens

import android.R.attr.text
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        if (selectedTab == 0 ) {
            Spacer(modifier = Modifier.height(16.dp))
            CalendarPanel(onCalendarClick = {

            })
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

@Composable
fun CalendarPanel(onCalendarClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onCalendarClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val days = listOf("5" to "S", "6" to "M", "7" to "T", "8" to "W", "9" to "T", "10" to "F", "11" to "S")
                days.forEach { (date, dayName) ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(35.dp, 40.dp)
                                .background(
                                    color = if (date == "8") Color(0xFFD4E157) else Color(0xFF2C2C2C),
                                    shape = RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = date,
                                color = if (date == "8") Color.Black else Color.White
                            )
                        }
                        Text(dayName, color = Color.Gray, fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("\uD83D\uDD25", fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text("2 days Streak", color = Color.White, style = MaterialTheme.typography.bodySmall )
            }
        }
    }
}