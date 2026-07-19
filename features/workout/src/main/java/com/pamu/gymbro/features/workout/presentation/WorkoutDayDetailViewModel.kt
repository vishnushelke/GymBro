package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDayUseCase
import com.pamu.gymbro.domain.usecase.workout.UpdateWorkoutExerciseWeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDayDetailViewModel @Inject constructor(
    private val getWorkoutDayUseCase: GetWorkoutDayUseCase,
    private val updateWorkoutExerciseWeightUseCase: UpdateWorkoutExerciseWeightUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _workoutDay = MutableStateFlow<WorkoutDay?>(null)
    val workoutDay: StateFlow<WorkoutDay?> = _workoutDay.asStateFlow()

    private val _unitPreference = MutableStateFlow("KG")
    val unitPreference: StateFlow<String> = _unitPreference.asStateFlow()

    init {
        observeUser()
    }

    private fun observeUser() {
        viewModelScope.launch {
            getUserUseCase().collect {
                it?.let { user ->
                    _unitPreference.value = user.unitPreference
                }
            }
        }
    }

    fun loadWorkoutDay(dayId: Long) {
        viewModelScope.launch {
            getWorkoutDayUseCase(dayId).collect {
                _workoutDay.value = it
            }
        }
    }

    fun updateWeight(exerciseWorkoutId: Long, weight: Double?) {
        viewModelScope.launch {
            updateWorkoutExerciseWeightUseCase(exerciseWorkoutId, weight, _unitPreference.value)
        }
    }
}
