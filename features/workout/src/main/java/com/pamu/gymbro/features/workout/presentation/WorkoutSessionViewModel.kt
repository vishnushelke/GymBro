package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.DailyStats
import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutSession
import com.pamu.gymbro.domain.usecase.workout.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutSessionViewModel @Inject constructor(
    private val getActiveWorkoutSessionUseCase: GetActiveWorkoutSessionUseCase,
    private val startWorkoutUseCase: StartWorkoutUseCase,
    private val completeSetUseCase: CompleteSetUseCase,
    private val finishWorkoutUseCase: FinishWorkoutUseCase,
    private val getWorkoutDayUseCase: GetWorkoutDayUseCase
) : ViewModel() {

    private val _timerSeconds = MutableStateFlow(0)
    val timerSeconds: StateFlow<Int> = _timerSeconds.asStateFlow()

    private val _timerType = MutableStateFlow(TimerType.NONE)
    val timerType: StateFlow<TimerType> = _timerType.asStateFlow()

    private val _isFinished = MutableSharedFlow<DailyStats>()
    val isFinished = _isFinished.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val session: StateFlow<WorkoutSession?> = getActiveWorkoutSessionUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val workoutDay: StateFlow<WorkoutDay?> = session
        .flatMapLatest { activeSession ->
            if (activeSession != null) {
                getWorkoutDayUseCase(activeSession.dayId)
            } else {
                flowOf(null)
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private var timerJob: Job? = null

    fun startWorkout(planId: Long, dayId: Long) {
        viewModelScope.launch {
            startWorkoutUseCase(planId, dayId)
        }
    }

    fun completeSet(reps: Int, weight: Double) {
        val currentSession = session.value ?: return
        val currentDay = workoutDay.value ?: return
        
        val currentExercise = currentDay.exercises.getOrNull(currentSession.currentExerciseIndex) ?: return
        val isLastSet = currentSession.currentSetNumber >= currentExercise.sets
        val isLastExercise = currentSession.currentExerciseIndex >= currentDay.exercises.size - 1

        viewModelScope.launch {
            completeSetUseCase(
                session = currentSession,
                reps = reps,
                weight = weight,
                isLastSetInExercise = isLastSet,
                isLastExerciseInWorkout = isLastExercise && isLastSet
            )
            
            if (isLastExercise && isLastSet) {
                finishWorkout()
            } else {
                startRestTimer()
            }
        }
    }

    private fun startRestTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            _timerType.value = TimerType.RECOVERY
            _timerSeconds.value = 10
            while (_timerSeconds.value > 0) {
                delay(1000)
                _timerSeconds.value -= 1
            }
            
            _timerType.value = TimerType.WALKING
            _timerSeconds.value = 10
            while (_timerSeconds.value > 0) {
                delay(1000)
                _timerSeconds.value -= 1
            }
            
            _timerType.value = TimerType.NONE
        }
    }

    fun finishWorkout() {
        val currentSession = session.value ?: return
        viewModelScope.launch {
            val stats = finishWorkoutUseCase(currentSession)
            _isFinished.emit(stats)
        }
    }

    enum class TimerType { NONE, RECOVERY, WALKING }
}
