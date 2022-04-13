package com.release.domain

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.release.domain.repositories.AuthRepository
import com.release.domain.usecase.auth.LogInUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import kotlin.math.log

@ExperimentalCoroutinesApi
class LogInUseCaseTest : BaseTest() {

    private lateinit var loginUseCase: LogInUseCase

    @Mock
    lateinit var authRepository: AuthRepository

    @Before
    override fun setUp(){
        super.setUp()
        loginUseCase = LogInUseCase(
            authRepository
        )
    }

    @Test
    fun `execute success`() = testCoroutine{
        val email = "email"
        val password = "password"
        val params = LogInUseCase.Params(email, password)

        given(authRepository.login(email, password))
            .willReturn(Unit)

        loginUseCase.execute(params)

        verify(authRepository, only()).login(email, password)
    }
}