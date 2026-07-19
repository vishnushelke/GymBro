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
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    onNavigateToReminders: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onStartWorkout: (Long, Long) -> Unit = { _, _ -> },
    onResumeWorkout: () -> Unit = {}
) {
    val summary by viewModel.summary.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val networkStatus by viewModel.networkStatus.collectAsState()
    val user by viewModel.user.collectAsState()
    val activeSession by viewModel.activeSession.collectAsState()
    
    var selectedTab by remember { mutableIntStateOf(1) } // Start on Home

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Explore, contentDescription = null) },
                    label = { Text("Explore") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { 
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(if (selectedTab == 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = user?.name?.firstOrNull()?.toString()?.uppercase() ?: "U",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                color = if (selectedTab == 2) Color.Black else Color.White
                            )
                        }
                    },
                    label = { Text("Profile") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (selectedTab) {
                0 -> ExploreTab(
                    onNavigateToExercises = onNavigateToExercises,
                    onWorkoutsClick = onNavigateToWorkouts,
                    onDietsClick = onNavigateToDiets,
                    onProgressClick = onNavigateToProgress
                )
                1 -> HomeTab(
                    user = user,
                    summary = summary,
                    networkStatus = networkStatus,
                    favorites = favorites,
                    onNavigateToProfile = onNavigateToProfile,
                    hasActiveSession = activeSession != null,
                    onStartWorkout = onStartWorkout,
                    onResumeWorkout = onResumeWorkout
                )
                2 -> ProfileContent(
                    user = user,
                    onEditProfileClick = onNavigateToProfile
                )
            }
        }
    }
}

@Composable
fun HomeTab(
    user: com.pamu.gymbro.domain.model.User?,
    summary: DashboardSummary?,
    networkStatus: ConnectivityObserver.Status,
    favorites: FavoriteItems?,
    onNavigateToProfile: () -> Unit,
    hasActiveSession: Boolean,
    onStartWorkout: (Long, Long) -> Unit,
    onResumeWorkout: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        // Hero Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?q=80&w=1000"),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
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
                Image(
                    painter = rememberAsyncImagePainter(model = com.pamu.gymbro.core.R.drawable.app_logo),
                    contentDescription = "GymBro Logo",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(bottom = 12.dp)
                )
                Text(
                    text = if (user != null) "Crush it, ${user.name}!" else "Welcome Back, Bro!",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        letterSpacing = (-1).sp
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

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            summary?.let { data ->
                DashboardHeader(
                    summary = data,
                    hasActiveSession = hasActiveSession,
                    onStartWorkout = onStartWorkout,
                    onResumeWorkout = onResumeWorkout
                )
                Spacer(modifier = Modifier.height(24.dp))
                QuickStatsRow(data)
                Spacer(modifier = Modifier.height(32.dp))
            }

            favorites?.let { favs ->
                if (favs.exercises.isNotEmpty() || favs.workouts.isNotEmpty() || favs.diets.isNotEmpty()) {
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
fun ExploreTab(
    onNavigateToExercises: () -> Unit,
    onWorkoutsClick: () -> Unit,
    onDietsClick: () -> Unit,
    onProgressClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Explore Tools",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        ExploreGrid(
            onLibraryClick = onNavigateToExercises,
            onWorkoutsClick = onWorkoutsClick,
            onDietsClick = onDietsClick,
            onProgressClick = onProgressClick
        )
    }
}

@Composable
fun ProfileContent(
    user: com.pamu.gymbro.domain.model.User?,
    onEditProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Profile",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )

        user?.let { profile ->
            UserProfileCard(user = profile, onEditClick = onEditProfileClick)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Additional Profile Options
            MenuOption(icon = Icons.Default.Settings, title = "App Settings")
            MenuOption(icon = Icons.Default.Help, title = "Help & Support")
            MenuOption(icon = Icons.Default.Logout, title = "Logout", color = MaterialTheme.colorScheme.error)
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun MenuOption(icon: ImageVector, title: String, color: Color = MaterialTheme.colorScheme.onSurface) {
    Surface(
        onClick = { /* Handle Click */ },
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = color.copy(alpha = 0.7f), modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = MaterialTheme.typography.bodyLarge, color = color)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = color.copy(alpha = 0.3f))
        }
    }
}

@Composable
fun UserProfileCard(user: com.pamu.gymbro.domain.model.User, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.name.firstOrNull()?.toString() ?: "U",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${user.level} • ${user.goal}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Profile", tint = MaterialTheme.colorScheme.primary)
                }
            }

            if (user.email.isNotBlank() || user.phone.isNotBlank() || user.age > 0 || user.sex.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(16.dp))
                
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (user.email.isNotBlank()) {
                        ProfileInfoItem(icon = Icons.Default.Email, text = user.email)
                    }
                    if (user.phone.isNotBlank()) {
                        ProfileInfoItem(icon = Icons.Default.Phone, text = user.phone)
                    }
                    if (user.age > 0 || user.sex.isNotBlank()) {
                        val ageText = if (user.age > 0) "${user.age} years" else ""
                        val sexText = user.sex
                        val combined = listOf(ageText, sexText).filter { it.isNotBlank() }.joinToString(" • ")
                        ProfileInfoItem(icon = Icons.Default.Info, text = combined)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoItem(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun DashboardHeader(
    summary: DashboardSummary,
    hasActiveSession: Boolean,
    onStartWorkout: (Long, Long) -> Unit,
    onResumeWorkout: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black)
                    )
                }
                
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { summary.workoutCompletionPercentage / 100f },
                        modifier = Modifier.size(56.dp),
                        strokeWidth = 6.dp,
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Text(
                        text = "${summary.workoutCompletionPercentage}%",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            val pId = summary.workoutPlanId
            val dId = summary.workoutDayId
            if (pId != null && dId != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { 
                        if (hasActiveSession) onResumeWorkout() 
                        else onStartWorkout(pId, dId)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = if (hasActiveSession) Icons.Default.PlayArrow else Icons.Default.FlashOn,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (hasActiveSession) "RESUME WORKOUT" else "START WORKOUT",
                        fontWeight = FontWeight.Black
                    )
                }
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
            label = "Burned",
            value = "${summary.caloriesBurned}",
            unit = "kcal",
            icon = Icons.Default.Whatshot,
            color = Color(0xFFFF5722),
            progress = summary.caloriesBurned.toFloat() / 500f // Placeholder daily goal
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
                title = "Exercise Guide",
                subtitle = "Master every move",
                image = "https://images.unsplash.com/photo-1540497077202-7c8a3999166f?q=80&w=500",
                icon = Icons.Default.Build,
                onClick = onLibraryClick
            )
            ExploreCard(
                modifier = Modifier.weight(1f),
                title = "Training Plans",
                subtitle = "Goal-based routines",
                image = "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?q=80&w=500",
                icon = Icons.Default.Star,
                onClick = onWorkoutsClick
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ExploreCard(
                modifier = Modifier.weight(1f),
                title = "Nutrition Hub",
                subtitle = "Fuel your body",
                image = "https://images.unsplash.com/photo-1490645935967-10de6ba17061?q=80&w=500",
                icon = Icons.AutoMirrored.Filled.List,
                onClick = onDietsClick
            )
            ExploreCard(
                modifier = Modifier.weight(1f),
                title = "Evolution Log",
                subtitle = "Track your growth",
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
