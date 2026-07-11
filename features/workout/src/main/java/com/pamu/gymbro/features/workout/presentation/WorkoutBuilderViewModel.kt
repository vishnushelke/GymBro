package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutExercise
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.usecase.exercise.GetExercisesUseCase
import com.pamu.gymbro.domain.usecase.workout.SaveWorkoutPlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutBuilderViewModel @Inject constructor(
    private val saveWorkoutPlanUseCase: SaveWorkoutPlanUseCase,
    private val getExercisesUseCase: GetExercisesUseCase
) : ViewModel() {

    private val _planName = MutableStateFlow("")
    val planName: StateFlow<String> = _planName.asStateFlow()

    private val _days = MutableStateFlow<List<WorkoutDay>>(emptyList())
    val days: StateFlow<List<WorkoutDay>> = _days.asStateFlow()

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    init {
        viewModelScope.launch {
            getExercisesUseCase().collect {
                _exercises.value = it
            }
        }
    }

    fun updatePlanName(name: String) {
        _planName.value = name
    }

    fun addDay() {
        val newDay = WorkoutDay(
            id = 0,
            workoutPlanId = 0,
            dayNumber = _days.value.size + 1,
            title = "Day ${_days.value.size + 1}",
            exercises = emptyList()
        )
        _days.value = _days.value + newDay
    }

    fun addExerciseToDay(dayNumber: Int, exercise: Exercise) {
        val updatedDays = _days.value.map { day ->
            if (day.dayNumber == dayNumber) {
                val newExercise = WorkoutExercise(
                    id = 0,
                    workoutDayId = 0,
                    exerciseId = exercise.id,
                    sets = 3,
                    reps = "10",
                    restSeconds = 60,
                    exercise = exercise
                )
                day.copy(exercises = day.exercises + newExercise)
            } else day
        }
        _days.value = updatedDays
    }

    fun savePlan(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val plan = WorkoutPlan(
                id = 0,
                name = _planName.value,
                level = "Custom",
                goal = "Custom",
                durationWeeks = 4,
                description = "Custom plan"
            )
            val result = saveWorkoutPlanUseCase(plan, _days.value)
            if (result.isSuccess) {
                onSuccess()
            }
        }
    }
}
