package com.pamu.gymbro.features.workout.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pamu.gymbro.core.presentation.components.VideoPlayer
import com.pamu.gymbro.domain.model.DailyStats
import com.pamu.gymbro.domain.model.WorkoutExercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutSessionScreen(
    viewModel: WorkoutSessionViewModel = hiltViewModel(),
    onFinish: (DailyStats) -> Unit,
    onClose: () -> Unit
) {
    val session by viewModel.session.collectAsState()
    val workoutDay by viewModel.workoutDay.collectAsState()
    val timerSeconds by viewModel.timerSeconds.collectAsState()
    val timerType by viewModel.timerType.collectAsState()
    val currentCalories by viewModel.currentCaloriesBurned.collectAsState()

    var showExitDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.isFinished.collect { stats ->
            onFinish(stats)
        }
    }

    if (session == null || workoutDay == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                val loadingText = when {
                    session == null -> "Fetching active session..."
                    workoutDay == null -> "Loading workout details..."
                    else -> "Preparing session..."
                }
                Text(loadingText, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                
                if (session != null && workoutDay == null) {
                    TextButton(
                        onClick = onClose,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Session Error? Exit", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
        return
    }

    val currentExercise = workoutDay?.exercises?.getOrNull(session?.currentExerciseIndex ?: 0)
    val exerciseDetails = currentExercise?.exercise

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "EXERCISE ${ (session?.currentExerciseIndex ?: 0) + 1 } OF ${workoutDay?.exercises?.size}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Black
                        ) 
                        Text(
                            text = "🔥 $currentCalories KCAL",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { showExitDialog = true }) {
                        Icon(Icons.Default.Close, contentDescription = "Exit")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Exercise Video
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                exerciseDetails?.videoFrontUrl?.let { url ->
                    VideoPlayer(videoUrl = url, modifier = Modifier.fillMaxSize())
                } ?: Icon(
                    Icons.Default.FitnessCenter, 
                    contentDescription = null, 
                    modifier = Modifier.size(64.dp).align(Alignment.Center),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Exercise Title
            Text(
                text = exerciseDetails?.name ?: "Unknown Exercise",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Text(
                text = "Target: ${currentExercise?.sets} Sets • ${currentExercise?.reps} Reps",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            if (currentExercise?.comfortableWeight != null) {
                Text(
                    text = "Working Weight: ${currentExercise.comfortableWeight} ${currentExercise.weightUnit}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Set Progress
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(currentExercise?.sets ?: 0) { index ->
                    val isCompleted = index < (session?.currentSetNumber ?: 1) - 1
                    val isCurrent = index == (session?.currentSetNumber ?: 1) - 1
                    
                    Box(
                        modifier = Modifier
                            .size(if (isCurrent) 40.dp else 32.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isCompleted -> MaterialTheme.colorScheme.primary
                                    isCurrent -> MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCompleted) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color.Black, modifier = Modifier.size(16.dp))
                        } else {
                            Text(
                                text = "${index + 1}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isCurrent) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Main Action Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                if (timerType == WorkoutSessionViewModel.TimerType.NONE) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Set ${session?.currentSetNumber}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Complete at least 12 reps before continuing.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { 
                                viewModel.completeSet(
                                    reps = 12, 
                                    weight = currentExercise?.comfortableWeight ?: 0.0
                                ) 
                            },
                            modifier = Modifier.fillMaxWidth().height(64.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("COMPLETED SET", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black)
                        }
                    }
                } else {
                    RestTimerView(
                        seconds = timerSeconds,
                        type = timerType
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Exit Workout?") },
            text = { Text("Your progress will be saved. You can resume this workout later from the dashboard.") },
            confirmButton = {
                Button(onClick = onClose) {
                    Text("EXIT")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("CANCEL")
                }
            }
        )
    }
}

@Composable
fun RestTimerView(seconds: Int, type: WorkoutSessionViewModel.TimerType) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val message = if (type == WorkoutSessionViewModel.TimerType.RECOVERY) "RECOVER..." else "WALK AROUND"
        val subMessage = if (type == WorkoutSessionViewModel.TimerType.RECOVERY) "Prepare for the next set..." else "Get your blood flowing!"
        val icon = if (type == WorkoutSessionViewModel.TimerType.RECOVERY) Icons.Default.Timer else Icons.AutoMirrored.Filled.DirectionsWalk

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary
        )
        
        Text(
            text = "$seconds",
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 80.sp),
            fontWeight = FontWeight.Black
        )
        
        Text(
            text = subMessage,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(24.dp))
        
        // Disabled button during recovery
        Button(
            onClick = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth().height(64.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("WAIT FOR TIMER...", fontWeight = FontWeight.Black)
        }
    }
}
