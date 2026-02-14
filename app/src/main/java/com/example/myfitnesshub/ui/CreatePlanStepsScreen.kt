package com.example.myfitnesshub.ui

import android.R.color.black
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Icon

@Composable
fun CreatePlanStep1Screen(navController: NavController) {
    var days by remember { mutableStateOf(3) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How many days per week?",
            style = typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items((1..7).toList()) { d ->
                val isSelected = days == d
                Box(
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected)Color(0xFFA0A0A0) else Color(0xFF1A1A1A))
                        .clickable { days = d },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${d}x per week ",
                        style = typography.headlineSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Button(
            onClick = {
                navController.navigate("create_plan_step2/$days")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E6B58))
        ) {
            Text("Next", color = Color.White, style = typography.titleMedium)
        }
    }
}


@Composable
fun CreatePlanStep2Screen(navController: NavController, daysPerWeek: Int) {
    var  selectedWeeks by remember { mutableStateOf(4) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 4.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                modifier = Modifier
                    .padding(24.dp)
                    .statusBarsPadding(),
                text = "Plan Duration",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold

            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items((1..52).toList()) { week ->
                val isSelected = selectedWeeks == week

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)

                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) Color(0xFFA0A0A0) else Color(0xFF1A1A1A))
                        .clickable { selectedWeeks = week }
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = if (week == 1) "$week Week" else "$week Weeks",
                            color = if (isSelected) Color.Black else Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium
                        )

                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Here you would move to the final step (e.g., adding exercises) Next
                // navController.navigate("final_step/$daysPerWeek/$selectedWeeks")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E6B58)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Create",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}