package com.pamu.gymbro.features.exercise.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.model.ExerciseCategory
import com.pamu.gymbro.domain.usecase.exercise.GetCategoriesUseCase
import com.pamu.gymbro.domain.usecase.exercise.GetExercisesUseCase
import com.pamu.gymbro.domain.usecase.favorite.FavoriteType
import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val getExercisesUseCase: GetExercisesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<ExerciseCategory>>(emptyList())
    val categories: StateFlow<List<ExerciseCategory>> = _categories.asStateFlow()

    private val _selectedCategoryId = MutableStateFlow<Long?>(null)
    val selectedCategoryId: StateFlow<Long?> = _selectedCategoryId.asStateFlow()

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    init {
        loadCategories()
        observeExercises()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect {
                _categories.value = it
            }
        }
    }

    private fun observeExercises() {
        viewModelScope.launch {
            _selectedCategoryId.collect { categoryId ->
                getExercisesUseCase(categoryId).collect {
                    _exercises.value = it
                }
            }
        }
    }

    fun selectCategory(categoryId: Long?) {
        _selectedCategoryId.value = categoryId
    }

    fun toggleFavorite(exercise: Exercise) {
        viewModelScope.launch {
            toggleFavoriteUseCase(exercise.id, FavoriteType.EXERCISE, !exercise.isFavorite)
        }
    }
}
