package com.pamu.gymbro.features.progress.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.pamu.gymbro.domain.model.ProgressEntry
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ProgressScreen(
    viewModel: ProgressViewModel = hiltViewModel()
) {
    val entries by viewModel.entries.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Progress")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                text = "Progress Tracking",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black),
                modifier = Modifier.padding(16.dp)
            )

            Box(modifier = Modifier.fillMaxSize()) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else if (entries.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShowChart,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No progress logged yet",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Start logging your weight to see your progress chart!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Button(
                            onClick = { showAddDialog = true },
                            modifier = Modifier.padding(top = 24.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Log Your First Entry")
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            WeightChart(entries = entries)
                            Spacer(modifier = Modifier.height(32.dp))
                        }

                        item {
                            Text(
                                text = "History",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }

                        items(entries, key = { it.id }) { entry ->
                            ProgressItem(
                                modifier = Modifier.animateItem(),
                                entry = entry
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddProgressDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { weight, bodyFat, notes ->
                viewModel.addEntry(weight, bodyFat, notes)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun WeightChart(entries: List<ProgressEntry>) {
    val colorPrimary = MaterialTheme.colorScheme.primary.toArgb()
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        AndroidView(
            factory = { context ->
                LineChart(context).apply {
                    description.isEnabled = false
                    setTouchEnabled(true)
                    setPinchZoom(true)
                }
            },
            update = { chart ->
                val chartEntries = entries.reversed().mapIndexed { index, entry ->
                    Entry(index.toFloat(), entry.weight)
                }
                val dataSet = LineDataSet(chartEntries, "Weight (kg)").apply {
                    color = colorPrimary
                    setCircleColor(colorPrimary)
                    lineWidth = 2f
                    circleRadius = 4f
                    setDrawCircleHole(false)
                    valueTextSize = 0f
                    setDrawFilled(true)
                    fillColor = colorPrimary
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                }
                chart.data = LineData(dataSet)
                chart.invalidate()
            },
            modifier = Modifier.fillMaxSize().padding(8.dp)
        )
    }
}

@Composable
fun ProgressItem(
    modifier: Modifier = Modifier,
    entry: ProgressEntry
) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = dateFormat.format(entry.date),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${entry.weight} kg",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            if (entry.bodyFat > 0) {
                Text(
                    text = "Body Fat: ${entry.bodyFat}%",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (entry.notes.isNotEmpty()) {
                Text(
                    text = entry.notes,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun AddProgressDialog(
    onDismiss: () -> Unit,
    onAdd: (Float, Float, String) -> Unit
) {
    var weight by remember { mutableStateOf("") }
    var bodyFat by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Log Progress", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = bodyFat,
                    onValueChange = { bodyFat = it },
                    label = { Text("Body Fat %") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss, modifier = Modifier.weight(1f).padding(end = 4.dp)) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            val w = weight.toFloatOrNull() ?: 0f
                            val bf = bodyFat.toFloatOrNull() ?: 0f
                            onAdd(w, bf, notes)
                        },
                        modifier = Modifier.weight(1f).padding(start = 4.dp)
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
}
