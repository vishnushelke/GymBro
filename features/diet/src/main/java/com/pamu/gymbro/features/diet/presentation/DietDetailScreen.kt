package com.pamu.gymbro.features.diet.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.pamu.gymbro.domain.model.Meal

@Composable
fun DietDetailScreen(
    planId: Long,
    viewModel: DietDetailViewModel = hiltViewModel()
) {
    val dietDetails by viewModel.dietDetails.collectAsState()

    LaunchedEffect(planId) {
        viewModel.loadDietDetails(planId)
    }

    dietDetails?.let { details ->
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = details.plan.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Daily Targets", style = MaterialTheme.typography.titleMedium)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Calories: ${details.plan.calories} kcal", modifier = Modifier.weight(1f))
                        Text(text = "Protein: ${details.plan.protein}g", modifier = Modifier.weight(1f))
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Carbs: ${details.plan.carbs}g", modifier = Modifier.weight(1f))
                        Text(text = "Fats: ${details.plan.fats}g", modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Meals",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(details.meals) { meal ->
                    MealItem(meal = meal)
                }
            }
        }
    }
}

@Composable
fun MealItem(meal: Meal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = meal.name, style = MaterialTheme.typography.titleLarge)
            Text(
                text = meal.mealType,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Row {
                Text(text = "${meal.calories} kcal")
                Text(text = " • P: ${meal.protein}g")
                Text(text = " • C: ${meal.carbs}g")
                Text(text = " • F: ${meal.fats}g")
            }
        }
    }
}
