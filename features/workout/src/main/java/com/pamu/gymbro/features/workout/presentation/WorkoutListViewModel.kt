package com.pamu.gymbro.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.WorkoutPlan
import com.pamu.gymbro.domain.usecase.favorite.FavoriteType
import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase
import com.pamu.gymbro.domain.usecase.workout.GetWorkoutPlansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    private val getWorkoutPlansUseCase: GetWorkoutPlansUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _workoutPlans = MutableStateFlow<List<WorkoutPlan>>(emptyList())
    val workoutPlans: StateFlow<List<WorkoutPlan>> = _workoutPlans.asStateFlow()

    init {
        loadWorkoutPlans()
    }

    private fun loadWorkoutPlans() {
        viewModelScope.launch {
            getWorkoutPlansUseCase().collect {
                _workoutPlans.value = it
            }
        }
    }

    fun toggleFavorite(plan: WorkoutPlan) {
        viewModelScope.launch {
            toggleFavoriteUseCase(plan.id, FavoriteType.WORKOUT, !plan.isFavorite)
        }
    }
}
