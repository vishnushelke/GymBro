package com.pamu.gymbro.features.exercise.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pamu.gymbro.core.presentation.components.VideoPlayer

@Composable
fun ExerciseDetailScreen(
    exerciseId: Long,
    viewModel: ExerciseDetailViewModel = hiltViewModel()
) {
    val exercise by viewModel.exercise.collectAsState()

    LaunchedEffect(exerciseId) {
        viewModel.loadExercise(exerciseId)
    }

    exercise?.let { item ->
        var selectedVideoTab by remember { mutableIntStateOf(0) }
        var playbackSpeed by remember { mutableFloatStateOf(1.0f) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val videoUrl = if (selectedVideoTab == 0) item.videoFrontUrl else item.videoSideUrl
            
            VideoPlayer(
                videoUrl = videoUrl,
                playbackSpeed = playbackSpeed
            )

            TabRow(selectedTabIndex = selectedVideoTab) {
                Tab(
                    selected = selectedVideoTab == 0,
                    onClick = { selectedVideoTab = 0 },
                    text = { Text("Front View") }
                )
                Tab(
                    selected = selectedVideoTab == 1,
                    onClick = { selectedVideoTab = 1 },
                    text = { Text("Side View") }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf(0.5f, 1.0f, 1.5f).forEach { speed ->
                    FilterChip(
                        selected = playbackSpeed == speed,
                        onClick = { playbackSpeed = speed },
                        label = { Text("${speed}x") }
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "${item.difficulty} • ${item.equipment}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Overview")
                Text(text = item.description, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Muscles Worked")
                Text(text = "Primary: ${item.primaryMuscle}", style = MaterialTheme.typography.bodyLarge)
                if (item.secondaryMuscles.isNotEmpty()) {
                    Text(text = "Secondary: ${item.secondaryMuscles}", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Benefits")
                Text(text = item.benefits, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Instructions")
                Text(text = item.instructions, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Common Mistakes")
                Text(text = item.commonMistakes, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Safety Tips")
                Text(text = item.safetyWarnings, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                SectionTitle("Variations")
                Text(text = "Beginner: ${item.beginnerVariation}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Intermediate: ${item.intermediateVariation}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Advanced: ${item.advancedVariation}", style = MaterialTheme.typography.bodySmall)
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
    }
}
