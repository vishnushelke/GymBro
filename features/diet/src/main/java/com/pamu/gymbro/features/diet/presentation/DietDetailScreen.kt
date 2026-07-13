package com.pamu.gymbro.features.diet.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pamu.gymbro.domain.model.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietDetailScreen(
    planId: Long,
    viewModel: DietDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val dietDetails by viewModel.dietDetails.collectAsState()

    LaunchedEffect(planId) {
        viewModel.loadDietDetails(planId)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (dietDetails == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }

        dietDetails?.let { details ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Hero Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    val image = when {
                        details.plan.name.lowercase().contains("veg") -> "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=800"
                        details.plan.name.lowercase().contains("protein") -> "https://images.unsplash.com/photo-1532550907401-a500c9a57435?q=80&w=800"
                        else -> "https://images.unsplash.com/photo-1490645935967-10de6ba17061?q=80&w=800"
                    }
                    
                    Image(
                        painter = rememberAsyncImagePainter(model = image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                                )
                            )
                    )
                    
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.3f))
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = details.plan.name,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "${details.plan.calories} kcal • ${details.plan.goal}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }

                // Macro Summary
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MacroCard(modifier = Modifier.weight(1f), label = "Protein", value = "${details.plan.protein}g", color = Color(0xFFFF5722))
                    MacroCard(modifier = Modifier.weight(1f), label = "Carbs", value = "${details.plan.carbs}g", color = Color(0xFF4CAF50))
                    MacroCard(modifier = Modifier.weight(1f), label = "Fats", value = "${details.plan.fats}g", color = Color(0xFFFFC107))
                }

                Text(
                    text = "Daily Meals",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(details.meals) { meal ->
                        MealItem(meal = meal)
                    }
                }
            }
        }
    }
}

@Composable
fun MacroCard(modifier: Modifier = Modifier, label: String, value: String, color: Color) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = color, fontWeight = FontWeight.Bold)
            Text(text = value, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun MealItem(meal: Meal) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Restaurant, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = meal.mealType,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${meal.calories} kcal • P: ${meal.protein}g • C: ${meal.carbs}g • F: ${meal.fats}g",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}
