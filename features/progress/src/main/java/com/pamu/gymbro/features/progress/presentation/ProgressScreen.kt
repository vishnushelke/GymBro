package com.pamu.gymbro.features.progress.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
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
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Progress")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Progress Tracking",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (entries.isNotEmpty()) {
                WeightChart(entries = entries)
                Spacer(modifier = Modifier.height(24.dp))
            }

            Text(text = "History", style = MaterialTheme.typography.titleLarge)
            
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(entries) { entry ->
                    ProgressItem(entry = entry)
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
fun ProgressItem(entry: ProgressEntry) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    Card(
        modifier = Modifier
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
