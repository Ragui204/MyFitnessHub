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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import com.example.myfitnesshub.viewmodel.Exercise
import com.example.myfitnesshub.viewmodel.WorkoutDay
import com.example.myfitnesshub.viewmodel.WorkoutSet
import com.example.myfitnesshub.viewmodel.WorkoutViewModel
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.myfitnesshub.viewmodel.ExerciseCategory

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
fun CreatePlanStep2Screen(navController: NavController, days: Int) {
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
                navController.navigate("create_plan_step3/$days/$selectedWeeks")
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

@Composable
fun CreatePlanStep3Screen(
    navController: NavController,
    viewModel: WorkoutViewModel,
    daysCount: Int,
    weeks: Int,
    //title: String
) {
    // 1. Set plan title.
    var planTitle by remember { mutableStateOf(viewModel.getNextPlanName()) }
    // 2. Initialize the days with default "Day 1", "Day 2"...
    val workoutDays = remember {
        mutableStateListOf(*Array(daysCount) { WorkoutDay(dayName = "Day ${it + 1}") })
    }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showExercisePicker by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(Color.Black).statusBarsPadding()) {
        //-- TITLE INPUT --
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically // Keeps the icon and text aligned
        ) {
            TextField(
                value = planTitle,
                onValueChange = { planTitle = it },
                placeholder = { Text("Plan Name", color = Color.Gray) },
                modifier = Modifier.weight(1f), // Takes up available space
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color(0xFF00FF9D) // Neon Green cursor
                )
            )

            // THE PEN ICON
            Icon(
                imageVector = Icons.Default.Edit, // You might need to import androidx.compose.material.icons.filled.Edit
                contentDescription = "Edit Title",
                tint = Color.Gray.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
        // --- TOP TABS ---
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Black,
            contentColor = Color(0xFF00FF9D), // Alpha Progression Neon Green
            edgePadding = 16.dp,
            divider = {} //removes default white line under tabs
        ) {
            workoutDays.forEachIndexed { index, day ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            day.dayName,
                            color = if (selectedTabIndex == index) Color.White else Color.Gray
                        )
                    }
                )
            }
            // 2. THE ADD BUTTON (as a Tab)
            Tab(
                selected = false,
                onClick = {
                    val nextDayNumber = workoutDays.size + 1
                    workoutDays.add(WorkoutDay(dayName = "Day $nextDayNumber"))
                    selectedTabIndex = workoutDays.size - 1
                },
                text = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Day",
                        tint = Color(0xFF00FF9D),
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
        }

        // --- EXERCISE LIST FOR CURRENT TAB ---
        LazyColumn(modifier = Modifier.weight(1f).background(Color.Black).padding(16.dp)) {
            //Display added index exercises
            itemsIndexed(workoutDays[selectedTabIndex].exercises) { index, exercise ->
                ExerciseEditCard(
                    exercise = exercise,
                    onUpdate = { updatedExercise ->
                        val currentDay = workoutDays[selectedTabIndex]
                        val newList = currentDay.exercises.toMutableList()
                        newList[index] = updatedExercise
                        workoutDays[selectedTabIndex] = currentDay.copy(exercises = newList)
                    },
                    onDelete = {
                        val currentDay = workoutDays[selectedTabIndex]
                        val newList = currentDay.exercises.toMutableList()
                        newList.removeAt(index)
                        workoutDays[selectedTabIndex] = currentDay.copy(exercises = newList)
                    }
                )
            }
            item {
                Button(
                    onClick = { showExercisePicker = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp), // Added padding for spacing from last card
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A1A1A)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF00FF9D)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Add Exercise to ${workoutDays[selectedTabIndex].dayName}",
                        color = Color.White
                    )
                }
            }
        }

        // --- BOTTOM SAVE BUTTON ---
        Button(
            enabled = planTitle.isNotBlank(),
            onClick = {
                viewModel.saveNewPlan(
                    title = planTitle,
                    weeks = weeks,
                    daysCount = workoutDays.size,
                    days = workoutDays.toList()
                )
                navController.navigate("workout_main") {
                    popUpTo("workout_main") { inclusive = true }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00FF9D)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Complete Plan",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

    // Exercise Picker Logic (Connects to your ViewModel's loadExercises list)
    if (showExercisePicker) {
        // You can use a ModalBottomSheet or a Dialog here to show allExercises
        ExercisePickerDialog(
            allCategories = viewModel.allExercises,
            onDismiss = { showExercisePicker = false },
            onSelect = { exerciseName ->
                val currentDay = workoutDays[selectedTabIndex]
                val newExercise = Exercise(
                    name = exerciseName,
                    sets = List(3) { WorkoutSet(weight = 0.0, reps = 8) }
                )
                val newList = currentDay.exercises.toMutableList()
                newList.add(newExercise)
                workoutDays[selectedTabIndex] = currentDay.copy(exercises = newList)
                showExercisePicker = false
            }

        )
    }
}

@Composable
fun ExercisePickerDialog(
    allCategories: List<ExerciseCategory>,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit
) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.7f),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Select Exercise", color = Color.White, style = typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    allCategories.forEach { category ->
                        item {
                            Text(category.category, color = Color(0xFF00FF9D), style = typography.labelLarge, modifier = Modifier.padding(vertical = 8.dp))
                        }
                        items(category.exercises) { exerciseName ->
                            Text(
                                text = exerciseName,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSelect(exerciseName) }
                                    .padding(vertical = 12.dp, horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun NumberStepper(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFF2C2C2C), RoundedCornerShape(8.dp))
                .padding(horizontal = 4.dp)
        ) {
            IconButton(
                onClick = { if (value > 1) onValueChange(value - 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(Icons.Default.Remove, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }

            Text(
                text = value.toString(),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = { onValueChange(value + 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }
    }
}
@Composable
fun ExerciseEditCard(
    exercise: Exercise,
    onUpdate: (Exercise) -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)) // Matching black theme
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(exercise.name, color = Color.White, style = typography.titleMedium)
                // Delete button
                Text(
                    "Remove",
                    color = Color.Red.copy(alpha = 0.7f),
                    style = typography.labelSmall,
                    modifier = Modifier.clickable { onDelete() }
                )
            }

            // Sets
            NumberStepper(label = "Sets", value = exercise.sets.size) { newValue ->
                val newSets = List(newValue) {
                    // Keep existing weight/reps if possible, else default to 8
                    exercise.sets.getOrNull(0) ?: WorkoutSet(0.0, 8)
                }
                onUpdate(exercise.copy(sets = newSets))
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Reps (updating the first set's reps for simplicity)
            val currentReps = exercise.sets.firstOrNull()?.reps ?: 8
            NumberStepper(label = "Reps", value = currentReps) { newValue ->
                val newSets = exercise.sets.map { it.copy(reps = newValue) }
                onUpdate(exercise.copy(sets = newSets))
            }
        }
    }
}