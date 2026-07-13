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
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    val todayEntry = viewModel.getTodayEntry()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = if (todayEntry == null) Icons.Default.Add else Icons.Default.Edit,
                    contentDescription = if (todayEntry == null) "Add Progress" else "Edit Today's Progress"
                )
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
                if (isLoading && entries.isEmpty()) {
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
                            imageVector = Icons.AutoMirrored.Filled.ShowChart,
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

                        items(entries) { entry ->
                            ProgressItem(entry = entry)
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddProgressDialog(
            existingEntry = todayEntry,
            onDismiss = { showAddDialog = false },
            onAdd = { weight, notes ->
                viewModel.addEntry(weight, notes)
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
                try {
                    val chartEntries = entries.reversed().mapIndexed { index, entry ->
                        Entry(index.toFloat(), entry.weight)
                    }
                    if (chartEntries.isNotEmpty()) {
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
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
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
    existingEntry: ProgressEntry? = null,
    onDismiss: () -> Unit,
    onAdd: (Float, String) -> Unit
) {
    var weight by remember { mutableStateOf(existingEntry?.weight?.toString() ?: "") }
    var notes by remember { mutableStateOf(existingEntry?.notes ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (existingEntry == null) "Log Progress" else "Edit Today's Progress",
                    style = MaterialTheme.typography.titleLarge
                )
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
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismiss, modifier = Modifier.weight(1f).padding(end = 4.dp)) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            val w = weight.toFloatOrNull() ?: 0f
                            onAdd(w, notes)
                        },
                        modifier = Modifier.weight(1f).padding(start = 4.dp)
                    ) {
                        Text(if (existingEntry == null) "Add" else "Update")
                    }
                }
            }
        }
    }
}
