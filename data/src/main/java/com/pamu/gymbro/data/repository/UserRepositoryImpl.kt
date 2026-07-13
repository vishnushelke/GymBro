package com.pamu.gymbro.data.repository

import com.pamu.gymbro.data.local.dao.UserDao
import com.pamu.gymbro.data.mapper.toDomain
import com.pamu.gymbro.data.mapper.toEntity
import com.pamu.gymbro.domain.model.User
import com.pamu.gymbro.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getUserProfile(): Flow<User?> {
        return userDao.getUserProfile().map { it?.toDomain() }
    }

    override suspend fun saveUserProfile(user: User) = withContext(Dispatchers.IO) {
        userDao.insertUserProfile(user.toEntity())
    }
}
