package com.pamu.gymbro.features.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
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
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://images.unsplash.com/photo-1541534741688-6078c6bfb5c5?q=80&w=1000"),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Dark scrim for overall image contrast
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            // Bottom gradient for text readability and smooth transition
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Text(
                    text = "Welcome Back, Bro!",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        letterSpacing = (-1).sp,
                        lineHeight = 40.sp
                    )
                )
                Text(
                    text = "Ready to crush your goals today?",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                )
            }
        }

        AnimatedVisibility(
            visible = networkStatus != ConnectivityObserver.Status.Available,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            OfflineIndicator()
        }

        if (summary == null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            summary?.let { data ->
                DashboardHeader(data)
                Spacer(modifier = Modifier.height(24.dp))
                
                QuickStatsRow(data)
                Spacer(modifier = Modifier.height(24.dp))
            }

            Text(
                text = "Explore",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExploreGrid(
                onLibraryClick = onNavigateToExercises,
                onWorkoutsClick = onNavigateToWorkouts,
                onDietsClick = onNavigateToDiets,
                onProgressClick = onNavigateToProgress
            )

            favorites?.let { favs ->
                if (favs.exercises.isNotEmpty() || favs.workouts.isNotEmpty() || favs.diets.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Your Favorites",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    FavoritesRow(favs)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun DashboardHeader(summary: DashboardSummary) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "TODAY'S PLAN",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = summary.todayWorkout ?: "Rest Day",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (summary.todayWorkout != null) "Time to sweat!" else "Active recovery is key",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { summary.workoutCompletionPercentage / 100f },
                    modifier = Modifier.size(64.dp),
                    strokeWidth = 6.dp,
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
                Text(
                    text = "${summary.workoutCompletionPercentage}%",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun QuickStatsRow(summary: DashboardSummary) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Calories",
            value = "${summary.caloriesConsumed}",
            unit = "kcal",
            icon = Icons.Default.Whatshot,
            color = Color(0xFFFF5722),
            progress = summary.caloriesConsumed.toFloat() / summary.calorieGoal.toFloat()
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Water",
            value = "${summary.waterIntakeMl}",
            unit = "ml",
            icon = Icons.Default.LocalDrink,
            color = Color(0xFF2196F3),
            progress = summary.waterIntakeMl.toFloat() / summary.waterGoalMl.toFloat()
        )
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    unit: String,
    icon: ImageVector,
    color: Color,
    progress: Float
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = label, style = MaterialTheme.typography.labelSmall)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = unit,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(color.copy(alpha = 0.2f), CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress.coerceIn(0f, 1f))
                        .height(4.dp)
                        .background(color, CircleShape)
                )
            }
        }
    }
}

@Composable
fun ExploreGrid(
    onLibraryClick: () -> Unit,
    onWorkoutsClick: () -> Unit,
    onDietsClick: () -> Unit,
    onProgressClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ExploreCard(
                modifier = Modifier.weight(1f),
                title = "Library",
                subtitle = "140+ Exercises",
                image = "https://images.unsplash.com/photo-1540497077202-7c8a3999166f?q=80&w=500",
                icon = Icons.Default.Build,
                onClick = onLibraryClick
            )
            ExploreCard(
                modifier = Modifier.weight(1f),
                title = "Workouts",
                subtitle = "Daily Plans",
                image = "https://images.unsplash.com/photo-1583454110551-21f2fa2ec617?q=80&w=500",
                icon = Icons.Default.Star,
                onClick = onWorkoutsClick
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ExploreCard(
                modifier = Modifier.weight(1f),
                title = "Diets",
                subtitle = "Healthy Food",
                image = "https://images.unsplash.com/photo-1490645935967-10de6ba17061?q=80&w=500",
                icon = Icons.AutoMirrored.Filled.List,
                onClick = onDietsClick
            )
            ExploreCard(
                modifier = Modifier.weight(1f),
                title = "Stats",
                subtitle = "Track Progress",
                image = "https://images.unsplash.com/photo-1460925895917-afdab827c52f?q=80&w=500",
                icon = Icons.AutoMirrored.Filled.ShowChart,
                onClick = onProgressClick
            )
        }
    }
}

@Composable
fun ExploreCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    image: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))))
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun FavoritesRow(favorites: FavoriteItems) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(favorites.exercises) { exercise ->
            FavoriteItemCard(name = exercise.name, type = "EXERCISE")
        }
        items(favorites.workouts) { workout ->
            FavoriteItemCard(name = workout.name, type = "WORKOUT")
        }
        items(favorites.diets) { diet ->
            FavoriteItemCard(name = diet.name, type = "DIET")
        }
    }
}

@Composable
fun FavoriteItemCard(name: String, type: String) {
    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = type,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                maxLines = 2,
                minLines = 2
            )
        }
    }
}

@Composable
fun OfflineIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Offline Mode: Using cached data",
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
