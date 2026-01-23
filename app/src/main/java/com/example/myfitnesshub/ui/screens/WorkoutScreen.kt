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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfitnesshub.viewmodel.WorkoutViewModel
import com.example.myfitnesshub.viewmodel.WorkoutPlan
import com.example.myfitnesshub.viewmodel.Exercise
import com.example.myfitnesshub.viewmodel.WorkoutSet
// Also add this for the list to work:
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Icon // The component itself
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon

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
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { viewModel.selectedTab(index) },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color(0xFF00FF9D) else Color.Gray,
                            style = typography.titleSmall
                        )
                    }
                )
            }
        }

        if (selectedTab == 0) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                // First item: The Calendar
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    CalendarPanel(onCalendarClick = { /* logic for popup */ })
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Plans",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                        // Add a '+' icon or button here for "Create New Plan"
                        Text(
                            text = "+ Create",
                            color = Color(0xFF00FF9D), // Neon Green
                            modifier = Modifier.clickable { /* Add new plan logic */ }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // List items: The actual self-made plans
                // Note: You need to define 'plans' in your WorkoutViewModel first
                items(viewModel.plans) { plan ->
                    PlanCard(plan = plan)
                }
            }
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
                Text("2 days Streak", color = Color.White, style = typography.bodySmall )
            }
        }
    }
}


@Composable
fun PlanCard(plan: WorkoutPlan) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable{ expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            //Header Row with Title and Arrow
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = plan.title, color = Color.White, style = typography.titleMedium)
                    Text(text = "${plan.exercises.size} Exercises", color = Color.Gray, style = typography.bodySmall)
                }
                //Arrow Symbol
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    tint = Color(0xFF00FF9D) // Neon Green to match your theme
                )
            }
            //Expanded Content
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                plan.exercises.forEach { exercise ->
                    Column(modifier = Modifier.padding(bottom = 8.dp)) {
                        Text(text = exercise.name, color = Color.White, fontWeight = FontWeight.Bold)

                        //Show sets and reps
                        exercise.sets.forEachIndexed {  index, set ->
                            Text(
                                text = "Set ${index + 1}: ${set.weight}kg x ${set.reps} reps",
                                color = Color.Gray,
                                style = typography.bodySmall,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}