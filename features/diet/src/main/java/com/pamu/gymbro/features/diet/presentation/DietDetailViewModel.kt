package com.pamu.gymbro.features.diet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.domain.usecase.diet.DietDetails
import com.pamu.gymbro.domain.usecase.diet.GetDietDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietDetailViewModel @Inject constructor(
    private val getDietDetailsUseCase: GetDietDetailsUseCase
) : ViewModel() {

    private val _dietDetails = MutableStateFlow<DietDetails?>(null)
    val dietDetails: StateFlow<DietDetails?> = _dietDetails.asStateFlow()

    fun loadDietDetails(planId: Long) {
        viewModelScope.launch {
            getDietDetailsUseCase(planId).collect {
                _dietDetails.value = it
            }
        }
    }
}
