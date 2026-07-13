package com.pamu.gymbro.features.diet.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pamu.gymbro.domain.model.DietPlan

@Composable
fun DietListScreen(
    viewModel: DietListViewModel = hiltViewModel(),
    onDietClick: (Long) -> Unit
) {
    val dietPlans by viewModel.dietPlans.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
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
                } else if (dietPlans.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
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
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(dietPlans, key = { it.id }) { plan ->
                            DietPlanItem(
                                modifier = Modifier.animateItem(),
                                plan = plan,
                                onClick = { onDietClick(plan.id) },
                                onToggleFavorite = { viewModel.toggleFavorite(plan) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DietPlanItem(
    modifier: Modifier = Modifier,
    plan: DietPlan,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    val image = when {
        plan.name.lowercase().contains("veg") -> "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=500"
        plan.name.lowercase().contains("protein") -> "https://images.unsplash.com/photo-1532550907401-a500c9a57435?q=80&w=500"
        else -> "https://images.unsplash.com/photo-1490645935967-10de6ba17061?q=80&w=500"
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

                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                ) {
                    Icon(
                        imageVector = if (plan.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (plan.isFavorite) MaterialTheme.colorScheme.primary else Color.White
                    )
                }
            }
        }
    }
}
