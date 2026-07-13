package com.pamu.gymbro.features.diet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Meal
import com.pamu.gymbro.domain.usecase.diet.GetDietDetailsUseCase
import com.pamu.gymbro.domain.usecase.diet.SaveDietPlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietBuilderViewModel @Inject constructor(
    private val saveDietPlanUseCase: SaveDietPlanUseCase,
    private val getDietDetailsUseCase: GetDietDetailsUseCase
) : ViewModel() {

    private var editingPlanId: Long = 0

    private val _planName = MutableStateFlow("")
    val planName: StateFlow<String> = _planName.asStateFlow()

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> = _meals.asStateFlow()

    private val _goal = MutableStateFlow("Muscle Gain")
    val goal: StateFlow<String> = _goal.asStateFlow()

    fun loadExistingPlan(planId: Long) {
        viewModelScope.launch {
            getDietDetailsUseCase(planId).first()?.let { details ->
                editingPlanId = details.plan.id
                _planName.value = details.plan.name
                _goal.value = details.plan.goal
                _meals.value = details.meals
            }
        }
    }

    fun updatePlanName(name: String) {
        _planName.value = name
    }

    fun updateGoal(goal: String) {
        _goal.value = goal
    }

    fun addMeal(name: String, type: String, calories: Int, protein: Int, carbs: Int, fats: Int) {
        val newMeal = Meal(
            id = 0,
            dietPlanId = 0,
            mealType = type,
            name = name,
            calories = calories,
            protein = protein,
            carbs = carbs,
            fats = fats
        )
        _meals.value = _meals.value + newMeal
    }

    fun removeMeal(meal: Meal) {
        _meals.value = _meals.value - meal
    }

    fun savePlan(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val totalCalories = _meals.value.sumOf { it.calories }
            val totalProtein = _meals.value.sumOf { it.protein }
            val totalCarbs = _meals.value.sumOf { it.carbs }
            val totalFats = _meals.value.sumOf { it.fats }

            val plan = DietPlan(
                id = editingPlanId,
                name = _planName.value,
                goal = _goal.value,
                calories = totalCalories,
                protein = totalProtein,
                carbs = totalCarbs,
                fats = totalFats,
                isFavorite = false
            )
            
            saveDietPlanUseCase(plan, _meals.value.map { it.copy(dietPlanId = editingPlanId) }).onSuccess {
                onSuccess()
            }
        }
    }
}
