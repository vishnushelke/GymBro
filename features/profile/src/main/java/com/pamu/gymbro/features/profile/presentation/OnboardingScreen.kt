package com.pamu.gymbro.features.profile.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

enum class OnboardingStep {
    NAME, GOAL, DIET, LEVEL
}

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onComplete: () -> Unit
) {
    val userState by viewModel.user.collectAsState()
    var currentStep by remember { mutableStateOf(OnboardingStep.NAME) }

    var name by remember { mutableStateOf("") }
    var isVegetarian by remember { mutableStateOf(false) }
    var selectedLevel by remember { mutableStateOf("Beginner") }
    var selectedGoal by remember { mutableStateOf("Maintenance") }

    LaunchedEffect(userState) {
        userState?.let {
            if (name.isEmpty()) name = it.name
            isVegetarian = it.isVegetarian
            selectedLevel = it.level
            selectedGoal = it.goal
        }
    }

    val backgroundImage = when (currentStep) {
        OnboardingStep.NAME -> "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?q=80&w=1000"
        OnboardingStep.GOAL -> "https://images.unsplash.com/photo-1550345332-09e3ac987658?q=80&w=1000"
        OnboardingStep.DIET -> "https://images.unsplash.com/photo-1490645935967-10de6ba17061?q=80&w=1000"
        OnboardingStep.LEVEL -> "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?q=80&w=1000"
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = rememberAsyncImagePainter(model = backgroundImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.7f),
                            Color.Black
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header / Progress
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentStep != OnboardingStep.NAME) {
                    IconButton(
                        onClick = {
                            currentStep = OnboardingStep.entries[currentStep.ordinal - 1]
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f))
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                } else {
                    Spacer(modifier = Modifier.size(48.dp))
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OnboardingStep.entries.forEach { step ->
                        Box(
                            modifier = Modifier
                                .size(if (step == currentStep) 12.dp else 8.dp)
                                .clip(CircleShape)
                                .background(if (step == currentStep) MaterialTheme.colorScheme.primary else Color.White.copy(alpha = 0.3f))
                        )
                    }
                }

                Spacer(modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.weight(0.5f))

            // Content Area with Animation
            AnimatedContent(
                targetState = currentStep,
                transitionSpec = {
                    if (targetState.ordinal > initialState.ordinal) {
                        (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it } + fadeOut())
                    } else {
                        (slideInHorizontally { -it } + fadeIn()).togetherWith(slideOutHorizontally { it } + fadeOut())
                    }
                },
                label = "onboarding_content"
            ) { step ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    when (step) {
                        OnboardingStep.NAME -> NameStep(name = name, onNameChange = { name = it })
                        OnboardingStep.GOAL -> GoalStep(selectedGoal = selectedGoal, onGoalSelected = { selectedGoal = it })
                        OnboardingStep.DIET -> DietStep(isVeg = isVegetarian, onDietChange = { isVegetarian = it })
                        OnboardingStep.LEVEL -> LevelStep(selectedLevel = selectedLevel, onLevelSelected = { selectedLevel = it })
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action Button
            Button(
                onClick = {
                    if (currentStep == OnboardingStep.LEVEL) {
                        viewModel.updateProfile(name, isVegetarian, selectedLevel, selectedGoal)
                        onComplete()
                    } else if (canGoNext(currentStep, name)) {
                        currentStep = OnboardingStep.entries[currentStep.ordinal + 1]
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.Black
                ),
                enabled = canGoNext(currentStep, name)
            ) {
                Text(
                    text = if (currentStep == OnboardingStep.LEVEL) "FINISH" else "CONTINUE",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

private fun canGoNext(step: OnboardingStep, name: String): Boolean {
    return when (step) {
        OnboardingStep.NAME -> name.isNotBlank()
        else -> true
    }
}

@Composable
private fun NameStep(name: String, onNameChange: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "What should we\ncall you?",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
        Text(
            text = "Your journey begins with a name.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter your name", color = Color.White.copy(alpha = 0.4f)) },
            shape = RoundedCornerShape(16.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                focusedContainerColor = Color.White.copy(alpha = 0.05f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
            ),
            singleLine = true
        )
    }
}

@Composable
private fun GoalStep(selectedGoal: String, onGoalSelected: (String) -> Unit) {
    val goals = listOf("Weight Loss", "Weight Gain", "Maintenance")
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Choose your goal",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Text(
            text = "We'll tailor your plans accordingly.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        goals.forEach { goal ->
            SelectableCard(
                text = goal,
                isSelected = selectedGoal == goal,
                onClick = { onGoalSelected(goal) }
            )
        }
    }
}

@Composable
private fun DietStep(isVeg: Boolean, onDietChange: (Boolean) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Food Preference",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Text(
            text = "Help us build your nutrition plan.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SelectableCard(
            text = "Vegetarian",
            subtitle = "Strictly no eggs or meat",
            isSelected = isVeg,
            onClick = { onDietChange(true) }
        )
        SelectableCard(
            text = "Non-Vegetarian",
            subtitle = "Includes eggs and meat",
            isSelected = !isVeg,
            onClick = { onDietChange(false) }
        )
    }
}

@Composable
private fun LevelStep(selectedLevel: String, onLevelSelected: (String) -> Unit) {
    val levels = listOf("Beginner", "Intermediate", "Expert")
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Experience Level",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Text(
            text = "How intense should we start?",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        levels.forEach { level ->
            SelectableCard(
                text = level,
                isSelected = selectedLevel == level,
                onClick = { onLevelSelected(level) }
            )
        }
    }
}

@Composable
private fun SelectableCard(
    text: String,
    subtitle: String? = null,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(if (subtitle != null) 80.dp else 64.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.White.copy(alpha = 0.1f),
            contentColor = if (isSelected) Color.Black else Color.White
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = text, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                if (subtitle != null) {
                    Text(text = subtitle, style = MaterialTheme.typography.labelSmall, color = if(isSelected) Color.Black.copy(alpha = 0.7f) else Color.White.copy(alpha = 0.5f))
                }
            }
        }
    }
}
