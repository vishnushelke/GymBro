package com.pamu.gymbro.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamu.gymbro.core.util.ConnectivityObserver
import com.pamu.gymbro.domain.model.DashboardSummary
import com.pamu.gymbro.domain.usecase.dashboard.GetDashboardSummaryUseCase
import com.pamu.gymbro.domain.usecase.favorite.FavoriteItems
import com.pamu.gymbro.domain.usecase.favorite.GetFavoriteItemsUseCase
import com.pamu.gymbro.domain.model.User
import com.pamu.gymbro.domain.usecase.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardSummaryUseCase: GetDashboardSummaryUseCase,
    private val getFavoriteItemsUseCase: GetFavoriteItemsUseCase,
    private val connectivityObserver: ConnectivityObserver,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _summary = MutableStateFlow<DashboardSummary?>(null)
    val summary: StateFlow<DashboardSummary?> = _summary.asStateFlow()

    private val _favorites = MutableStateFlow<FavoriteItems?>(null)
    val favorites: StateFlow<FavoriteItems?> = _favorites.asStateFlow()

    private val _networkStatus = MutableStateFlow(ConnectivityObserver.Status.Unavailable)
    val networkStatus: StateFlow<ConnectivityObserver.Status> = _networkStatus.asStateFlow()

    init {
        loadSummary()
        loadFavorites()
        observeNetwork()
        observeUser()
    }

    private fun observeUser() {
        viewModelScope.launch {
            getUserUseCase().collect {
                _user.value = it
            }
        }
    }

    private fun loadSummary() {
        viewModelScope.launch {
            getDashboardSummaryUseCase().collect {
                _summary.value = it
            }
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            getFavoriteItemsUseCase().collect {
                _favorites.value = it
            }
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            connectivityObserver.observe().collect {
                _networkStatus.value = it
            }
        }
    }
}
