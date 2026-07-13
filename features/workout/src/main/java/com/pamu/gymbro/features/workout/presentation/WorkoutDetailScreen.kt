package com.pamu.gymbro.features.workout.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pamu.gymbro.domain.model.WorkoutDay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDetailScreen(
    planId: Long,
    viewModel: WorkoutDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val workoutDetails by viewModel.workoutDetails.collectAsState()

    LaunchedEffect(planId) {
        viewModel.loadWorkoutDetails(planId)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (workoutDetails == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }

        workoutDetails?.let { details ->
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
                    val image = when(details.plan.name.lowercase()) {
                        "chest" -> "https://images.unsplash.com/photo-1571019614242-c5c5dee9f50b?q=80&w=800"
                        "back" -> "https://images.unsplash.com/photo-1605296867304-46d5465a13f1?q=80&w=800"
                        "legs" -> "https://images.unsplash.com/photo-1434608519344-49d77a699e1d?q=80&w=800"
                        "arms" -> "https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?q=80&w=800"
                        else -> "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?q=80&w=800"
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
                            text = "${details.plan.level} • ${details.plan.goal}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }

                Text(
                    text = "Weekly Schedule",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(details.days) { day ->
                        WorkoutDayItem(modifier = Modifier.animateItem(), day = day)
                    }
                    item { Spacer(modifier = Modifier.height(32.dp)) }
                }
            }
        }
    }
}

@Composable
fun WorkoutDayItem(modifier: Modifier = Modifier, day: WorkoutDay) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Day ${day.dayNumber}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = day.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
