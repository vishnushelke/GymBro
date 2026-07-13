package com.pamu.gymbro.features.exercise.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pamu.gymbro.core.presentation.components.VideoPlayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exerciseId: Long,
    viewModel: ExerciseDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val exercise by viewModel.exercise.collectAsState()

    LaunchedEffect(exerciseId) {
        viewModel.loadExercise(exerciseId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (exercise == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }

        exercise?.let { item ->
            var playbackSpeed by remember { mutableFloatStateOf(1.0f) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .padding(bottom = 32.dp)
            ) {
                // Hero Section with Video
                Box(modifier = Modifier.fillMaxWidth().height(350.dp)) {
                    VideoPlayer(
                        videoUrl = item.videoFrontUrl,
                        playbackSpeed = playbackSpeed,
                        modifier = Modifier.fillMaxSize()
                    )
                    
                    // Back Button
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.3f))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    // Bottom Gradient Overlay
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, MaterialTheme.colorScheme.background)
                                )
                            )
                    )
                }

                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-0.5).sp
                        )
                    )
                    
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DetailChip(label = item.difficulty, icon = Icons.Default.Star)
                        DetailChip(label = item.equipment, icon = Icons.Default.FitnessCenter)
                    }

                    // Playback Speed Selector
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Playback Speed:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        listOf(0.5f, 1.0f, 1.5f).forEach { speed ->
                            FilterChip(
                                selected = playbackSpeed == speed,
                                onClick = { playbackSpeed = speed },
                                label = { Text("${speed}x") },
                                shape = CircleShape
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), thickness = 0.5.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

                    DetailSection(title = "Description", content = item.description, icon = Icons.Default.Info)
                    DetailSection(title = "Benefits", content = item.benefits, icon = Icons.Default.Star)
                    DetailSection(title = "Instructions", content = item.instructions, icon = Icons.AutoMirrored.Filled.List)
                    
                    if (item.commonMistakes.isNotEmpty()) {
                        DetailSection(
                            title = "Common Mistakes",
                            content = item.commonMistakes,
                            icon = Icons.Default.Warning,
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Target Muscles",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        mainAxisSpacing = 8.dp,
                        crossAxisSpacing = 8.dp
                    ) {
                        MuscleChip(item.primaryMuscle, isPrimary = true)
                        item.secondaryMuscles.split(",").forEach { muscle ->
                            if (muscle.trim().isNotEmpty()) {
                                MuscleChip(muscle.trim(), isPrimary = false)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailChip(label: String, icon: ImageVector) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon, 
                contentDescription = null, 
                modifier = Modifier.size(14.dp), 
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = label, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun DetailSection(
    title: String,
    content: String,
    icon: ImageVector,
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
            color = contentColor.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun MuscleChip(muscle: String, isPrimary: Boolean) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = if (isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        contentColor = if (isPrimary) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Text(
            text = muscle,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    mainAxisSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    crossAxisSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    content: @Composable () -> Unit
) {
    androidx.compose.ui.layout.Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeholders = measurables.map { it.measure(constraints.copy(minWidth = 0, minHeight = 0)) }
        val rows = mutableListOf<List<androidx.compose.ui.layout.Placeable>>()
        var currentRow = mutableListOf<androidx.compose.ui.layout.Placeable>()
        var currentRowWidth = 0

        placeholders.forEach { placeable ->
            if (currentRowWidth + placeable.width + mainAxisSpacing.toPx() > constraints.maxWidth && currentRow.isNotEmpty()) {
                rows.add(currentRow)
                currentRow = mutableListOf()
                currentRowWidth = 0
            }
            currentRow.add(placeable)
            currentRowWidth += (placeable.width + mainAxisSpacing.toPx()).toInt()
        }
        if (currentRow.isNotEmpty()) rows.add(currentRow)

        val width = rows.maxOfOrNull { row -> row.sumOf { it.width } + (row.size - 1) * mainAxisSpacing.toPx().toInt() } ?: 0
        val height = rows.sumOf { row -> row.maxOf { it.height } } + (rows.size - 1) * crossAxisSpacing.toPx().toInt()

        layout(width, height) {
            var y = 0
            rows.forEach { row ->
                var x = 0
                row.forEach { placeable ->
                    placeable.place(x, y)
                    x += placeable.width + mainAxisSpacing.toPx().toInt()
                }
                y += row.maxOf { it.height } + crossAxisSpacing.toPx().toInt()
            }
        }
    }
}
