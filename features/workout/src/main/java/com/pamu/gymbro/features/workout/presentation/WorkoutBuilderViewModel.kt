package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutExercise
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.usecase.exercise.GetExercisesUseCase
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutDetailsUseCase
import com.pamu.gymbro.domain.usecase.workout.SaveWorkoutPlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutBuilderViewModel @Inject constructor(
    private val saveWorkoutPlanUseCase: SaveWorkoutPlanUseCase,
    private val getExercisesUseCase: GetExercisesUseCase,
    private val getWorkoutDetailsUseCase: GetWorkoutDetailsUseCase
) : ViewModel() {

    private var editingPlanId: Long = 0

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

    fun loadExistingPlan(planId: Long) {
        viewModelScope.launch {
            getWorkoutDetailsUseCase(planId).first()?.let { details ->
                editingPlanId = details.plan.id
                _planName.value = details.plan.name
                _days.value = details.days
            }
        }
    }

    fun updatePlanName(name: String) {
        _planName.value = name
    }

    fun updateDayTitle(dayNumber: Int, title: String) {
        val updatedDays = _days.value.map { day ->
            if (day.dayNumber == dayNumber) day.copy(title = title) else day
        }
        _days.value = updatedDays
    }

    fun addDay() {
        val nextDayNum = (_days.value.maxOfOrNull { it.dayNumber } ?: 0) + 1
        val newDay = WorkoutDay(
            id = 0,
            workoutPlanId = editingPlanId,
            dayNumber = nextDayNum,
            title = "Body Part Target",
            exercises = emptyList()
        )
        _days.value = _days.value + newDay
    }

    fun removeDay(dayNumber: Int) {
        _days.value = _days.value.filter { it.dayNumber != dayNumber }
    }

    fun addExerciseToDay(dayNumber: Int, exercise: Exercise) {
        val updatedDays = _days.value.map { day ->
            if (day.dayNumber == dayNumber) {
                val newExercise = WorkoutExercise(
                    id = 0,
                    workoutDayId = day.id,
                    exerciseId = exercise.id,
                    sets = 3,
                    reps = "12",
                    restSeconds = 60,
                    exercise = exercise
                )
                day.copy(exercises = day.exercises + newExercise)
            } else day
        }
        _days.value = updatedDays
    }

    fun removeExerciseFromDay(dayNumber: Int, exerciseId: Long) {
        val updatedDays = _days.value.map { day ->
            if (day.dayNumber == dayNumber) {
                day.copy(exercises = day.exercises.filter { it.exerciseId != exerciseId })
            } else day
        }
        _days.value = updatedDays
    }

    fun savePlan(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val plan = WorkoutPlan(
                id = editingPlanId,
                name = _planName.value,
                level = "Custom",
                goal = "Custom",
                durationWeeks = 12,
                description = "Custom workout plan created by user."
            )
            val result = saveWorkoutPlanUseCase(plan, _days.value)
            if (result.isSuccess) {
                onSuccess()
            }
        }
    }
}
