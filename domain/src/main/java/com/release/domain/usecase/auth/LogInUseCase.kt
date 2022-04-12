package com.release.domain.usecase.auth

import com.release.domain.repositories.AuthRepository
import com.release.domain.usecase.UseCase
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, LogInUseCase.Params>() {

    override suspend fun execute(params: Params) {
        authRepository.login(params.email, params.password)
    }

    data class Params(val email: String, val password: String)
}
