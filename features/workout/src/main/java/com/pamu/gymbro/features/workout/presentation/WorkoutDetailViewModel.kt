package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDetailsUseCase
import com.pamu.gymbro.domain.usecase.workout.WorkoutDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    private val getWorkoutDetailsUseCase: GetWorkoutDetailsUseCase
) : ViewModel() {

    private val _workoutDetails = MutableStateFlow<WorkoutDetails?>(null)
    val workoutDetails: StateFlow<WorkoutDetails?> = _workoutDetails.asStateFlow()

    fun loadWorkoutDetails(planId: Long) {
        viewModelScope.launch {
            getWorkoutDetailsUseCase(planId).collect {
                _workoutDetails.value = it
            }
        }
    }
}
