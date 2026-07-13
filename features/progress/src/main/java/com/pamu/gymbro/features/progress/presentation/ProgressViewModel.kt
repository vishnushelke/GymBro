package com.pamu.gymbro.features.progress.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.ProgressEntry
import com.pamu.gymbro.domain.usecase.progress.AddProgressEntryUseCase
import com.pamu.gymbro.domain.usecase.progress.GetProgressEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val getProgressEntriesUseCase: GetProgressEntriesUseCase,
    private val addProgressEntryUseCase: AddProgressEntryUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val entries: StateFlow<List<ProgressEntry>> = getProgressEntriesUseCase()
        .onEach { _isLoading.value = false }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    fun getTodayEntry(): ProgressEntry? {
        val today = Date()
        return entries.value.find { isSameDay(it.date, today) }
    }

    fun addEntry(weight: Float, notes: String) {
        viewModelScope.launch {
            val today = Date()
            val existingEntry = getTodayEntry()
            
            val entry = ProgressEntry(
                id = existingEntry?.id ?: 0L,
                date = existingEntry?.date ?: today,
                weight = weight,
                chest = 0f,
                waist = 0f,
                hips = 0f,
                arms = 0f,
                thighs = 0f,
                bodyFat = 0f,
                notes = notes
            )
            addProgressEntryUseCase(entry)
        }
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val fmt = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return fmt.format(date1) == fmt.format(date2)
    }
}
