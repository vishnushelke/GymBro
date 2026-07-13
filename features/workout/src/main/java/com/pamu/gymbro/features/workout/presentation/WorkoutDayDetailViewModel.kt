package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDayDetailViewModel @Inject constructor(
    private val getWorkoutDayUseCase: GetWorkoutDayUseCase
) : ViewModel() {

    private val _workoutDay = MutableStateFlow<WorkoutDay?>(null)
    val workoutDay: StateFlow<WorkoutDay?> = _workoutDay.asStateFlow()

    fun loadWorkoutDay(dayId: Long) {
        viewModelScope.launch {
            getWorkoutDayUseCase(dayId).collect {
                _workoutDay.value = it
            }
        }
    }
}
