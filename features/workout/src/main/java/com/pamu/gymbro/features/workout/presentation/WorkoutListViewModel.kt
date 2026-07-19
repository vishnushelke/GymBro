package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.User
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.repository.WorkoutSessionRepository
import com.pamu.gymbro.domain.usecase.favorite.FavoriteType
import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase
import com.pamu.gymbro.domain.usecase.workout.DeleteWorkoutPlanUseCase
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutPlansUseCase
import com.pamu.gymbro.domain.usecase.workout.SaveWorkoutPlanUseCase
import com.pamu.gymbro.domain.usecase.workout.WorkoutGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    private val getWorkoutPlansUseCase: GetWorkoutPlansUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val saveWorkoutPlanUseCase: SaveWorkoutPlanUseCase,
    private val deleteWorkoutPlanUseCase: DeleteWorkoutPlanUseCase,
    private val sessionRepository: WorkoutSessionRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val workoutPlans: StateFlow<List<WorkoutPlan>> = getWorkoutPlansUseCase()
        .onEach { _isLoading.value = false }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    private var currentUser: User? = null

    init {
        observeUser()
    }

    private fun observeUser() {
        viewModelScope.launch {
            combine(getUserUseCase(), workoutPlans) { user, plans ->
                user to plans
            }.collect { (user, plans) ->
                currentUser = user
                if (user != null) {
                    checkAndAutoUpdateDefaultPlan(user, plans)
                }
            }
        }
    }

    private fun checkAndAutoUpdateDefaultPlan(user: User, plans: List<WorkoutPlan>) {
        // Wait for initial DB emission before deciding to generate
        if (_isLoading.value && plans.isEmpty()) return 

        val existingDefault = plans.find { it.name.startsWith("Default") || it.name.startsWith("Official") }
        if (existingDefault != null) {
            if (existingDefault.level != user.level || existingDefault.goal != user.goal) {
                generateDefaultWorkout(replaceExistingId = existingDefault.id)
            }
        }
    }

    fun hasDefaultPlan(): WorkoutPlan? {
        return workoutPlans.value.find { it.name.startsWith("Default") || it.name.startsWith("Official") }
    }

    fun hasCustomPlan(): WorkoutPlan? {
        return workoutPlans.value.find { !it.name.startsWith("Default") && !it.name.startsWith("Official") }
    }

    fun generateDefaultWorkout(replaceExistingId: Long? = null) {
        viewModelScope.launch {
            val user = currentUser ?: return@launch
            _isLoading.value = true
            try {
                if (replaceExistingId != null) {
                    deleteWorkoutPlanUseCase(replaceExistingId)
                    sessionRepository.deleteActiveSession()
                }
                val (plan, days) = WorkoutGenerator.generateDefaultPlan(user.level, user.goal)
                saveWorkoutPlanUseCase(plan, days)
            } catch (e: Exception) {
                e.printStackTrace()
                _isLoading.value = false
            }
        }
    }

    fun deletePlan(planId: Long) {
        viewModelScope.launch {
            deleteWorkoutPlanUseCase(planId)
        }
    }

    fun toggleFavorite(plan: WorkoutPlan) {
        viewModelScope.launch {
            toggleFavoriteUseCase(plan.id, FavoriteType.WORKOUT, !plan.isFavorite)
        }
    }
}
