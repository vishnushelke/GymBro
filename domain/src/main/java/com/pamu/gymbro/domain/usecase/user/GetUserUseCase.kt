package com.pamu.gymbro.domain.usecase.user

import com.pamu.gymbro.domain.model.User
import com.pamu.gymbro.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<User?> = repository.getUserProfile()
}
