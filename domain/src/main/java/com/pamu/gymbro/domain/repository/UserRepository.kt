package com.pamu.gymbro.domain.repository

import com.pamu.gymbro.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserProfile(): Flow<User?>
    suspend fun saveUserProfile(user: User)
}
