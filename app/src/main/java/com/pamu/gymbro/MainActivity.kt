package com.pamu.gymbro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pamu.gymbro.features.diet.presentation.DietBuilderScreen
import com.pamu.gymbro.features.diet.presentation.DietDetailScreen
import com.pamu.gymbro.features.diet.presentation.DietListScreen
import com.pamu.gymbro.features.exercise.presentation.ExerciseDetailScreen
import com.pamu.gymbro.features.exercise.presentation.ExerciseListScreen
import com.pamu.gymbro.features.home.presentation.HomeScreen
import com.pamu.gymbro.features.progress.presentation.ProgressScreen
import com.pamu.gymbro.features.reminder.presentation.ReminderSettingsScreen
import com.pamu.gymbro.features.workout.presentation.WorkoutBuilderScreen
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
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(
                                onNavigateToExercises = {
                                    navController.navigate("exercises")
                                },
                                onNavigateToWorkouts = {
                                    navController.navigate("workouts")
                                },
                                onNavigateToDiets = {
                                    navController.navigate("diets")
                                },
                                onNavigateToProgress = {
                                    navController.navigate("progress")
                                },
                                onNavigateToReminders = {
                                    navController.navigate("reminders")
                                }
                            )
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
                            ExerciseDetailScreen(exerciseId = id)
                        }
                        composable("workouts") {
                            WorkoutListScreen(
                                onWorkoutClick = { id ->
                                    navController.navigate("workout_detail/$id")
                                },
                                onAddWorkoutClick = {
                                    navController.navigate("workout_builder")
                                }
                            )
                        }
                        composable(
                            "workout_detail/{planId}",
                            arguments = listOf(navArgument("planId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getLong("planId") ?: 0L
                            WorkoutDetailScreen(planId = id)
                        }
                        composable("workout_builder") {
                            WorkoutBuilderScreen(
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
