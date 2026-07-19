package com.pamu.gymbro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pamu.gymbro.features.diet.presentation.DietBuilderScreen
import com.pamu.gymbro.features.diet.presentation.DietDetailScreen
import com.pamu.gymbro.features.diet.presentation.DietListScreen
import com.pamu.gymbro.features.exercise.presentation.ExerciseDetailScreen
import com.pamu.gymbro.features.exercise.presentation.ExerciseListScreen
import com.pamu.gymbro.features.home.presentation.*
import com.pamu.gymbro.features.profile.presentation.OnboardingScreen
import com.pamu.gymbro.features.profile.presentation.ProfileScreen
import com.pamu.gymbro.features.progress.presentation.ProgressScreen
import com.pamu.gymbro.features.reminder.presentation.ReminderSettingsScreen
import com.pamu.gymbro.features.workout.presentation.WorkoutBuilderScreen
import com.pamu.gymbro.features.workout.presentation.WorkoutDayDetailScreen
import com.pamu.gymbro.features.workout.presentation.WorkoutDetailScreen
import com.pamu.gymbro.features.workout.presentation.WorkoutListScreen
import com.pamu.gymbro.ui.theme.GymBroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymBroTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                val isProfileCompleted by mainViewModel.isProfileCompleted.collectAsState()

                if (isProfileCompleted == null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else if (isProfileCompleted == false) {
                    OnboardingScreen(onComplete = { /* Flow will update via DB emission */ })
                } else {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    val bottomBarScreens = listOf("explore", "home", "profile_tab")
                    val showBottomBar = bottomBarScreens.any { it == currentDestination?.route }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            if (showBottomBar) {
                                NavigationBar(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    tonalElevation = 8.dp
                                ) {
                                    val dashboardViewModel: DashboardViewModel = hiltViewModel()
                                    val user by dashboardViewModel.user.collectAsState()

                                    NavigationBarItem(
                                        selected = currentDestination?.hierarchy?.any { it.route == "explore" } == true,
                                        onClick = {
                                            navController.navigate("explore") {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = { Icon(Icons.Default.Search, contentDescription = null) },
                                        label = { Text("Explore") }
                                    )
                                    NavigationBarItem(
                                        selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                                        onClick = {
                                            navController.navigate("home") {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                        label = { Text("Home") }
                                    )
                                    NavigationBarItem(
                                        selected = currentDestination?.hierarchy?.any { it.route == "profile_tab" } == true,
                                        onClick = {
                                            navController.navigate("profile_tab") {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = {
                                            Box(
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .clip(CircleShape)
                                                    .background(
                                                        if (currentDestination?.route == "profile_tab") 
                                                            MaterialTheme.colorScheme.primary 
                                                        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = user?.name?.firstOrNull()?.toString()?.uppercase() ?: "U",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.Black,
                                                    color = if (currentDestination?.route == "profile_tab") Color.Black else Color.White
                                                )
                                            }
                                        },
                                        label = { Text("Profile") }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("home") {
                                val viewModel: DashboardViewModel = hiltViewModel()
                                val summary by viewModel.summary.collectAsState()
                                val favorites by viewModel.favorites.collectAsState()
                                val networkStatus by viewModel.networkStatus.collectAsState()
                                val user by viewModel.user.collectAsState()

                                HomeTab(
                                    user = user,
                                    summary = summary,
                                    networkStatus = networkStatus,
                                    favorites = favorites,
                                    onNavigateToProfile = { navController.navigate("profile") }
                                )
                            }
                            composable("explore") {
                                ExploreTab(
                                    onNavigateToExercises = { navController.navigate("exercises") },
                                    onWorkoutsClick = { navController.navigate("workouts") },
                                    onDietsClick = { navController.navigate("diets") },
                                    onProgressClick = { navController.navigate("progress") }
                                )
                            }
                            composable("profile_tab") {
                                val viewModel: DashboardViewModel = hiltViewModel()
                                val user by viewModel.user.collectAsState()
                                ProfileContent(
                                    user = user,
                                    onEditProfileClick = { navController.navigate("profile") }
                                )
                            }
                            composable("profile") {
                                ProfileScreen(onBack = { navController.popBackStack() })
                            }
                            composable("exercises") {
                                ExerciseListScreen(
                                    onExerciseClick = { id ->
                                        navController.navigate("exercise_detail/$id")
                                    }
                                )
                            }
                            composable(
                                "exercise_detail/{exerciseId}",
                                arguments = listOf(navArgument("exerciseId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val id = backStackEntry.arguments?.getLong("exerciseId") ?: 0L
                                ExerciseDetailScreen(
                                    exerciseId = id,
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable("workouts") {
                                WorkoutListScreen(
                                    onWorkoutClick = { id ->
                                        navController.navigate("workout_detail/$id")
                                    },
                                    onAddWorkoutClick = {
                                        navController.navigate("workout_builder")
                                    },
                                    onEditWorkoutClick = { id ->
                                        navController.navigate("workout_builder?planId=$id")
                                    }
                                )
                            }
                            composable(
                                "workout_detail/{planId}",
                                arguments = listOf(navArgument("planId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val id = backStackEntry.arguments?.getLong("planId") ?: 0L
                                WorkoutDetailScreen(
                                    planId = id,
                                    onDayClick = { dayId ->
                                        navController.navigate("workout_day_detail/$dayId")
                                    }
                                )
                            }
                            composable(
                                "workout_day_detail/{dayId}",
                                arguments = listOf(navArgument("dayId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val dayId = backStackEntry.arguments?.getLong("dayId") ?: 0L
                                WorkoutDayDetailScreen(
                                    dayId = dayId,
                                    onExerciseClick = { id ->
                                        navController.navigate("exercise_detail/$id")
                                    },
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable("workout_builder?planId={planId}",
                                arguments = listOf(navArgument("planId") { 
                                    type = NavType.LongType
                                    defaultValue = 0L
                                })
                            ) { backStackEntry ->
                                val planId = backStackEntry.arguments?.getLong("planId")?.takeIf { it > 0L }
                                WorkoutBuilderScreen(
                                    planId = planId,
                                    onBack = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                            composable("diets") {
                                DietListScreen(
                                    onDietClick = { id ->
                                        navController.navigate("diet_detail/$id")
                                    },
                                    onAddDietClick = {
                                        navController.navigate("diet_builder")
                                    },
                                    onEditDietClick = { id ->
                                        navController.navigate("diet_builder?planId=$id")
                                    }
                                )
                            }
                            composable(
                                "diet_detail/{planId}",
                                arguments = listOf(navArgument("planId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val id = backStackEntry.arguments?.getLong("planId") ?: 0L
                                DietDetailScreen(
                                    planId = id,
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                            composable("diet_builder?planId={planId}",
                                arguments = listOf(navArgument("planId") { 
                                    type = NavType.LongType
                                    defaultValue = 0L
                                })
                            ) { backStackEntry ->
                                val planId = backStackEntry.arguments?.getLong("planId")?.takeIf { it > 0L }
                                DietBuilderScreen(
                                    planId = planId,
                                    onBack = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                            composable("progress") {
                                ProgressScreen()
                            }
                            composable("reminders") {
                                ReminderSettingsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
