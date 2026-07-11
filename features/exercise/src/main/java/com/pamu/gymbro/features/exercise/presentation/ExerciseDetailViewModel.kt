package com.pamu.gymbro.features.exercise.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.Exercise
import com.pamu.gymbro.domain.usecase.exercise.GetExerciseByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase
) : ViewModel() {

    private val _exercise = MutableStateFlow<Exercise?>(null)
    val exercise: StateFlow<Exercise?> = _exercise.asStateFlow()

    fun loadExercise(id: Long) {
        viewModelScope.launch {
            _exercise.value = getExerciseByIdUseCase(id)
        }
    }
}
