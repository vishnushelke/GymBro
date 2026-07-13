package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.User
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.usecase.favorite.FavoriteType
import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase
import com.pamu.gymbro.domain.usecase.workout.DeleteWorkoutPlanUseCase
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutPlansUseCase
import com.pamu.gymbro.domain.usecase.workout.SaveWorkoutPlanUseCase
import com.pamu.gymbro.domain.usecase.workout.WorkoutGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    private val getWorkoutPlansUseCase: GetWorkoutPlansUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val saveWorkoutPlanUseCase: SaveWorkoutPlanUseCase,
    private val deleteWorkoutPlanUseCase: DeleteWorkoutPlanUseCase
) : ViewModel() {

    private val _workoutPlans = MutableStateFlow<List<WorkoutPlan>>(emptyList())
    val workoutPlans: StateFlow<List<WorkoutPlan>> = _workoutPlans.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentUser: User? = null

    init {
        loadWorkoutPlans()
        observeUser()
    }

    private fun loadWorkoutPlans() {
        viewModelScope.launch {
            getWorkoutPlansUseCase().collect {
                _workoutPlans.value = it
                _isLoading.value = false
            }
        }
    }

    private fun observeUser() {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                currentUser = user
                checkAndAutoUpdateDefaultPlan()
            }
        }
    }

    private fun checkAndAutoUpdateDefaultPlan() {
        val user = currentUser ?: return
        val existingDefault = hasDefaultPlan()
        if (existingDefault != null) {
            // Check if level or goal changed
            if (existingDefault.level != user.level || existingDefault.goal != user.goal) {
                generateDefaultWorkout(replaceExistingId = existingDefault.id)
            }
        } else {
            // Auto generate for first time after onboarding
            generateDefaultWorkout()
        }
    }

    fun hasDefaultPlan(): WorkoutPlan? {
        return workoutPlans.value.find { it.name.startsWith("Default") }
    }

    fun hasCustomPlan(): WorkoutPlan? {
        return workoutPlans.value.find { !it.name.startsWith("Default") }
    }

    fun generateDefaultWorkout(replaceExistingId: Long? = null) {
        viewModelScope.launch {
            val user = currentUser ?: return@launch
            _isLoading.value = true
            
            replaceExistingId?.let { deleteWorkoutPlanUseCase(it) }

            val (plan, days) = WorkoutGenerator.generateDefaultPlan(user.level, user.goal)
            saveWorkoutPlanUseCase(plan, days)
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
