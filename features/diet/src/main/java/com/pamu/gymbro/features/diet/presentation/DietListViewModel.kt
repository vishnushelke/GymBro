package com.pamu.gymbro.features.diet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.usecase.diet.GetDietPlansUseCase
import com.pamu.gymbro.domain.usecase.favorite.FavoriteType
import com.pamu.gymbro.domain.usecase.favorite.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietListViewModel @Inject constructor(
    private val getDietPlansUseCase: GetDietPlansUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _dietPlans = MutableStateFlow<List<DietPlan>>(emptyList())
    val dietPlans: StateFlow<List<DietPlan>> = _dietPlans.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadDietPlans()
    }

    private fun loadDietPlans() {
        viewModelScope.launch {
            getDietPlansUseCase().collect {
                _dietPlans.value = it
                _isLoading.value = false
            }
        }
    }

    fun toggleFavorite(plan: DietPlan) {
        viewModelScope.launch {
            toggleFavoriteUseCase(plan.id, FavoriteType.DIET, !plan.isFavorite)
        }
    }
}
