package com.release.domain.usecase.auth

import com.release.domain.repositories.AuthRepository
import com.release.domain.usecase.None
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, None>() {

    override suspend fun execute(params: None) {
        authRepository.logout()
    }
}
