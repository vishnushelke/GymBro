package com.pamu.gymbro.features.workout.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pamu.gymbro.domain.model.WorkoutDay

@Composable
fun WorkoutDetailScreen(
    planId: Long,
    viewModel: WorkoutDetailViewModel = hiltViewModel()
) {
    val workoutDetails by viewModel.workoutDetails.collectAsState()

    LaunchedEffect(planId) {
        viewModel.loadWorkoutDetails(planId)
    }

    workoutDetails?.let { details ->
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = details.plan.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(details.days) { day ->
                    WorkoutDayItem(day = day)
                }
            }
        }
    }
}

@Composable
fun WorkoutDayItem(day: WorkoutDay) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Day ${day.dayNumber}: ${day.title}", style = MaterialTheme.typography.titleLarge)
            // Exercises could be listed here
        }
    }
}
