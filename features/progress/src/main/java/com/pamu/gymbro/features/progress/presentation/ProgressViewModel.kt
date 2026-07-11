package com.pamu.gymbro.features.progress.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.ProgressEntry
import com.pamu.gymbro.domain.usecase.progress.AddProgressEntryUseCase
import com.pamu.gymbro.domain.usecase.progress.GetProgressEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val getProgressEntriesUseCase: GetProgressEntriesUseCase,
    private val addProgressEntryUseCase: AddProgressEntryUseCase
) : ViewModel() {

    private val _entries = MutableStateFlow<List<ProgressEntry>>(emptyList())
    val entries: StateFlow<List<ProgressEntry>> = _entries.asStateFlow()

    init {
        loadEntries()
    }

    private fun loadEntries() {
        viewModelScope.launch {
            getProgressEntriesUseCase().collect {
                _entries.value = it
            }
        }
    }

    fun addEntry(weight: Float, bodyFat: Float, notes: String) {
        viewModelScope.launch {
            val entry = ProgressEntry(
                id = 0,
                date = Date(),
                weight = weight,
                chest = 0f,
                waist = 0f,
                hips = 0f,
                arms = 0f,
                thighs = 0f,
                bodyFat = bodyFat,
                notes = notes
            )
            addProgressEntryUseCase(entry)
        }
    }
}
