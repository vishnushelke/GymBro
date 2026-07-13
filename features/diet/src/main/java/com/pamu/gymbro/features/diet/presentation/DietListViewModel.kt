package com.pamu.gymbro.features.diet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.model.DietPlan
import com.pamu.gymbro.domain.model.Meal
import com.pamu.gymbro.domain.model.User
import com.pamu.gymbro.domain.usecase.diet.DeleteDietPlanUseCase
import com.pamu.gymbro.domain.usecase.diet.GetDietPlansUseCase
import com.pamu.gymbro.domain.usecase.diet.SaveDietPlanUseCase
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietListViewModel @Inject constructor(
    private val getDietPlansUseCase: GetDietPlansUseCase,
    private val saveDietPlanUseCase: SaveDietPlanUseCase,
    private val deleteDietPlanUseCase: DeleteDietPlanUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val dietPlans: StateFlow<List<DietPlan>> = getDietPlansUseCase()
        .onEach { _isLoading.value = false }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private var currentUser: User? = null

    init {
        observeUser()
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
            // Check if level, goal or veg preference changed in plan name/goal
            val isVeg = user.isVegetarian
            val expectedNamePrefix = "Default ${if (isVeg) "Vegetarian" else "Non-Vegetarian"}"
            
            if (!existingDefault.name.startsWith(expectedNamePrefix) || existingDefault.goal != user.goal) {
                generateDefaultDiet(replaceExistingId = existingDefault.id)
            }
        } else {
            // Auto generate for first time after onboarding
            generateDefaultDiet()
        }
    }

    fun hasDefaultPlan(): DietPlan? {
        return dietPlans.value.find { it.name.startsWith("Default") }
    }

    fun hasCustomPlan(): DietPlan? {
        return dietPlans.value.find { !it.name.startsWith("Default") }
    }

    fun deletePlan(planId: Long) {
        viewModelScope.launch {
            deleteDietPlanUseCase(planId)
        }
    }

    fun generateDefaultDiet(replaceExistingId: Long? = null) {
        viewModelScope.launch {
            val user = currentUser ?: return@launch
            _isLoading.value = true
            
            replaceExistingId?.let { 
                deleteDietPlanUseCase(it)
            }

            val isVeg = user.isVegetarian
            val level = user.level
            val goal = user.goal
            
            val name = if (isVeg) "Vegetarian" else "Non-Vegetarian"
            
            val meals = when (goal) {
                "Weight Loss" -> if (isVeg) {
                    listOf(
                        Meal(0, 0, "Breakfast", "Oats with Skimmed Milk and Berries", 300, 12, 45, 5),
                        Meal(0, 0, "Pre-Workout", "Black Coffee and a Small Apple", 80, 0, 20, 0),
                        Meal(0, 0, "Post-Workout", "Plant Protein Shake with Water", 120, 25, 2, 1),
                        Meal(0, 0, "Lunch", "Mixed Vegetable Salad with Tofu", 350, 25, 15, 12),
                        Meal(0, 0, "Snack", "A handful of Roasted Chana", 150, 8, 20, 3),
                        Meal(0, 0, "Dinner", "Clear Vegetable Soup and Sautéed Mushrooms", 250, 15, 10, 10)
                    )
                } else {
                    listOf(
                        Meal(0, 0, "Breakfast", "Egg White Omelet with Spinach", 250, 24, 5, 10),
                        Meal(0, 0, "Pre-Workout", "Green Tea and an Orange", 70, 1, 18, 0),
                        Meal(0, 0, "Post-Workout", "Whey Protein in Water", 120, 25, 3, 1),
                        Meal(0, 0, "Lunch", "Grilled Chicken Breast with Steamed Broccoli", 400, 50, 10, 8),
                        Meal(0, 0, "Snack", "One Boiled Egg", 70, 6, 1, 5),
                        Meal(0, 0, "Dinner", "Baked Fish (Cod/Tilapia) with Asparagus", 300, 40, 5, 10)
                    )
                }
                "Weight Gain" -> if (isVeg) {
                    listOf(
                        Meal(0, 0, "Breakfast", "Paneer Paratha with Thick Curd", 600, 20, 70, 25),
                        Meal(0, 0, "Pre-Workout", "Banana with 2 tbsp Peanut Butter", 400, 10, 40, 20),
                        Meal(0, 0, "Post-Workout", "Banana Milkshake with Whey/Soy Protein", 450, 35, 60, 10),
                        Meal(0, 0, "Lunch", "Rajma Chawal with Ghee and Salad", 750, 25, 110, 20),
                        Meal(0, 0, "Snack", "Mixed Nuts, Dates and Dried Figs", 350, 10, 40, 15),
                        Meal(0, 0, "Dinner", "Tofu/Paneer Curry with Brown Rice and Roti", 650, 30, 80, 20)
                    )
                } else {
                    listOf(
                        Meal(0, 0, "Breakfast", "3 Whole Eggs and 4 Slices of Buttered Toast", 650, 28, 60, 35),
                        Meal(0, 0, "Pre-Workout", "Peanut Butter Sandwich and a Banana", 450, 15, 65, 18),
                        Meal(0, 0, "Post-Workout", "Whey Protein with Whole Milk and Oats", 500, 40, 50, 12),
                        Meal(0, 0, "Lunch", "Chicken Biryani with Raita", 850, 45, 120, 25),
                        Meal(0, 0, "Snack", "Greek Yogurt with Granola and Honey", 350, 20, 45, 10),
                        Meal(0, 0, "Dinner", "Mutton/Beef Curry with Steamed Rice", 800, 55, 90, 30)
                    )
                }
                else -> if (isVeg) { // Maintenance
                    listOf(
                        Meal(0, 0, "Breakfast", "Oats with Milk, Nuts and Honey", 450, 18, 65, 12),
                        Meal(0, 0, "Pre-Workout", "Banana and a few Almonds", 250, 5, 40, 10),
                        Meal(0, 0, "Post-Workout", "Soy Protein Shake with an Apple", 280, 22, 40, 4),
                        Meal(0, 0, "Lunch", "Dal Tadka, Rice, and Mixed Veg Sabzi", 550, 22, 85, 15),
                        Meal(0, 0, "Snack", "Greek Yogurt with Mixed Seeds", 200, 15, 15, 8),
                        Meal(0, 0, "Dinner", "Paneer/Tofu Stir Fry with 2 Rotis", 480, 28, 45, 18)
                    )
                } else {
                    listOf(
                        Meal(0, 0, "Breakfast", "2 Whole Eggs and 2 Egg Whites on Toast", 450, 30, 40, 18),
                        Meal(0, 0, "Pre-Workout", "Apple with 1 tbsp Peanut Butter", 250, 5, 30, 10),
                        Meal(0, 0, "Post-Workout", "Whey Protein Shake and a Banana", 270, 26, 35, 2),
                        Meal(0, 0, "Lunch", "Grilled Chicken Breast with Brown Rice", 550, 45, 60, 12),
                        Meal(0, 0, "Snack", "Handful of Trail Mix", 200, 8, 15, 12),
                        Meal(0, 0, "Dinner", "Baked Chicken/Fish with Sweet Potato", 500, 45, 40, 12)
                    )
                }
            }

            val totalCals = meals.sumOf { it.calories }
            val totalPro = meals.sumOf { it.protein }
            val totalCarb = meals.sumOf { it.carbs }
            val totalFat = meals.sumOf { it.fats }

            val plan = DietPlan(0, "Default $name ($level) - $goal", goal, totalCals, totalPro, totalCarb, totalFat, false)
            
            saveDietPlanUseCase(plan, meals).onFailure {
                _isLoading.value = false
            }
        }
    }
}
