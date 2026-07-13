package com.pamu.gymbro.domain.usecase.user

import com.pamu.gymbro.domain.model.User
import com.pamu.gymbro.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUserProfile(user)
    }
}
