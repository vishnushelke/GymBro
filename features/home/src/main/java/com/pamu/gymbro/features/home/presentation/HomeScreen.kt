package com.pamu.gymbro.features.home.presentation

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pamu.gymbro.core.util.ConnectivityObserver
import com.pamu.gymbro.domain.model.DashboardSummary
import com.pamu.gymbro.domain.usecase.favorite.FavoriteItems

@Composable
fun HomeScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onNavigateToExercises: () -> Unit = {},
    onNavigateToWorkouts: () -> Unit = {},
    onNavigateToDiets: () -> Unit = {},
    onNavigateToProgress: () -> Unit = {},
    onNavigateToReminders: () -> Unit = {}
) {
    val summary by viewModel.summary.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val networkStatus by viewModel.networkStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        if (networkStatus != ConnectivityObserver.Status.Available) {
            OfflineIndicator()
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        summary?.let { data ->
            DashboardCards(
                summary = data,
                onWeightClick = onNavigateToProgress
            )
        }

        favorites?.let { favs ->
            if (favs.exercises.isNotEmpty() || favs.workouts.isNotEmpty() || favs.diets.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "My Favorites", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                FavoritesRow(favs)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Quick Actions", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onNavigateToExercises,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Library")
                }
                Button(
                    onClick = onNavigateToWorkouts,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Workouts")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onNavigateToDiets,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Diets")
                }
                Button(
                    onClick = onNavigateToReminders,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Reminders")
                }
            }
        }
    }
}

@Composable
fun OfflineIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "You are currently offline. Using local data.",
            color = MaterialTheme.colorScheme.onErrorContainer,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun FavoritesRow(favorites: FavoriteItems) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(favorites.exercises) { exercise ->
            FavoriteCard(name = exercise.name, type = "Exercise")
        }
        items(favorites.workouts) { workout ->
            FavoriteCard(name = workout.name, type = "Workout")
        }
        items(favorites.diets) { diet ->
            FavoriteCard(name = diet.name, type = "Diet")
        }
    }
}

@Composable
fun FavoriteCard(name: String, type: String) {
    Card(modifier = Modifier.width(160.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = type, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
            Text(text = name, style = MaterialTheme.typography.titleMedium, maxLines = 1)
        }
    }
}

@Composable
fun DashboardCards(
    summary: DashboardSummary,
    onWeightClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Today's Workout", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = summary.todayWorkout ?: "No Workout Scheduled",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "${summary.workoutCompletionPercentage}% Completed",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onWeightClick() }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Weight", style = MaterialTheme.typography.labelLarge)
                    Text(text = "${summary.currentWeightKg} kg", style = MaterialTheme.typography.titleMedium)
                }
            }
            Card(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Streak", style = MaterialTheme.typography.labelLarge)
                    Text(text = "${summary.currentStreakDays} Days", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Calories", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = "${summary.caloriesConsumed} / ${summary.calorieGoal} kcal",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Water Intake", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = "${summary.waterIntakeMl} / ${summary.waterGoalMl} ml",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
