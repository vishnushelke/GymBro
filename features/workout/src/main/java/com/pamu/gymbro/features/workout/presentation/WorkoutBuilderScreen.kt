package com.pamu.gymbro.features.workout.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.pamu.gymbro.domain.model.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutBuilderScreen(
    viewModel: WorkoutBuilderViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val planName by viewModel.planName.collectAsState()
    val days by viewModel.days.collectAsState()
    val exercises by viewModel.exercises.collectAsState()
    
    var showExercisePickerForDay by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Build Workout") },
                actions = {
                    Button(onClick = { viewModel.savePlan(onBack) }) {
                        Text("Save")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = planName,
                onValueChange = viewModel::updatePlanName,
                label = { Text("Workout Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Days", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = viewModel::addDay) {
                    Icon(Icons.Default.Add, contentDescription = "Add Day")
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(days) { day ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = day.title, style = MaterialTheme.typography.titleMedium)
                            
                            day.exercises.forEach { workoutExercise ->
                                Text(text = "- ${workoutExercise.exercise?.name ?: "Unknown"}")
                            }

                            Button(
                                onClick = { showExercisePickerForDay = day.dayNumber },
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Add Exercise")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showExercisePickerForDay != null) {
        ExercisePickerDialog(
            exercises = exercises,
            onDismiss = { showExercisePickerForDay = null },
            onExerciseSelected = { exercise ->
                viewModel.addExerciseToDay(showExercisePickerForDay!!, exercise)
                showExercisePickerForDay = null
            }
        )
    }
}

@Composable
fun ExercisePickerDialog(
    exercises: List<Exercise>,
    onDismiss: () -> Unit,
    onExerciseSelected: (Exercise) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.fillMaxWidth().height(400.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Select Exercise", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(exercises) { exercise ->
                        Text(
                            text = exercise.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onExerciseSelected(exercise) }
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
