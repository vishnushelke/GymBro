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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val exercises: StateFlow<List<Exercise>> = kotlinx.coroutines.flow.combine(
        _selectedCategoryId,
        _searchQuery
    ) { categoryId, query ->
        categoryId to query
    }.flatMapLatest { (categoryId, query) ->
        getExercisesUseCase(categoryId, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect {
                _categories.value = it
            }
        }
    }

    fun selectCategory(categoryId: Long?) {
        _selectedCategoryId.value = categoryId
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(exercise: Exercise) {
        viewModelScope.launch {
            toggleFavoriteUseCase(exercise.id, FavoriteType.EXERCISE, !exercise.isFavorite)
        }
    }
}
