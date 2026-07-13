package com.pamu.gymbro.features.diet.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pamu.gymbro.domain.model.DietPlan

@Composable
fun DietListScreen(
    viewModel: DietListViewModel = hiltViewModel(),
    onDietClick: (Long) -> Unit,
    onAddDietClick: () -> Unit,
    onEditDietClick: (Long) -> Unit
) {
    val dietPlans by viewModel.dietPlans.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var showDefaultDietPicker by remember { mutableStateOf(false) }
    var showReplaceConfirmation by remember { mutableStateOf(false) }
    var showCustomReplaceConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SmallFloatingActionButton(
                    onClick = { showDefaultDietPicker = true },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = "Default Diet")
                }
                
                FloatingActionButton(
                    onClick = {
                        val existing = viewModel.hasCustomPlan()
                        if (existing != null) {
                            showCustomReplaceConfirmation = true
                        } else {
                            onAddDietClick()
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Custom Diet")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                text = "Diet Plans",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.5).sp
                ),
                modifier = Modifier.padding(16.dp)
            )

            Box(modifier = Modifier.fillMaxSize()) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    if (dietPlans.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Restaurant,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "No diet plans available",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                            Button(
                                onClick = { showDefaultDietPicker = true },
                                modifier = Modifier.padding(top = 16.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Generate Default Diet")
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(dietPlans, key = { it.id }) { plan ->
                                val isCustom = !plan.name.startsWith("Default")
                                DietPlanItem(
                                    modifier = Modifier.animateItem(),
                                    plan = plan,
                                    onClick = { onDietClick(plan.id) },
                                    onEditClick = if (isCustom) { { onEditDietClick(plan.id) } } else null
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDefaultDietPicker) {
        // We no longer need the picker dialog if we use the saved profile.
        // But for consistency with your request "replace if exists", 
        // I will keep a simpler "Replace existing?" check if they click the magic button.
        val existing = viewModel.hasDefaultPlan()
        if (existing != null) {
            showReplaceConfirmation = true
        } else {
            viewModel.generateDefaultDiet()
        }
        showDefaultDietPicker = false
    }

    if (showReplaceConfirmation) {
        AlertDialog(
            onDismissRequest = { showReplaceConfirmation = false },
            title = { Text("Replace Default Plan?") },
            text = { Text("You already have a default diet plan. Replacing it will regenerate it based on your profile.") },
            confirmButton = {
                Button(onClick = {
                    val existing = viewModel.hasDefaultPlan()
                    viewModel.generateDefaultDiet(replaceExistingId = existing?.id)
                    showReplaceConfirmation = false
                }) {
                    Text("REPLACE")
                }
            },
            dismissButton = {
                TextButton(onClick = { showReplaceConfirmation = false }) {
                    Text("CANCEL")
                }
            }
        )
    }

    if (showCustomReplaceConfirmation) {
        AlertDialog(
            onDismissRequest = { showCustomReplaceConfirmation = false },
            title = { Text("Custom Plan Exists") },
            text = { Text("You already have a custom diet plan. You can either edit the existing plan or replace it with a new one.") },
            confirmButton = {
                Button(onClick = {
                    val existing = viewModel.hasCustomPlan()
                    existing?.let { viewModel.deletePlan(it.id) }
                    showCustomReplaceConfirmation = false
                    onAddDietClick()
                }) {
                    Text("REPLACE")
                }
            },
            dismissButton = {
                Row {
                    TextButton(onClick = { showCustomReplaceConfirmation = false }) {
                        Text("CANCEL")
                    }
                    TextButton(onClick = { 
                        val existing = viewModel.hasCustomPlan()
                        existing?.let { onEditDietClick(it.id) }
                        showCustomReplaceConfirmation = false
                    }) {
                        Text("EDIT")
                    }
                }
            }
        )
    }
}

@Composable
fun DietPlanItem(
    modifier: Modifier = Modifier,
    plan: DietPlan,
    onClick: () -> Unit,
    onEditClick: (() -> Unit)? = null
) {
    val image = remember(plan.name) {
        when {
            plan.name.lowercase().contains("veg") -> "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=500"
            plan.name.lowercase().contains("protein") -> "https://images.unsplash.com/photo-1532550907401-a500c9a57435?q=80&w=500"
            else -> "https://images.unsplash.com/photo-1490645935967-10de6ba17061?q=80&w=500"
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
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
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
            )
            
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = plan.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${plan.calories} kcal • ${plan.goal}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }

                if (onEditClick != null) {
                    IconButton(
                        onClick = onEditClick,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Diet",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}
