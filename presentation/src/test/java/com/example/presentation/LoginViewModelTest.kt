package com.example.presentation

import com.example.presentation.auth.LoginViewModel
import com.nhaarman.mockitokotlin2.*
import com.release.domain.usecase.auth.LogInUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: LoginViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = createViewModel()
    }

    @Test
    fun `send correct email and password, open Inspections screen`() = testCoroutine {
        val email = "email"
        val password = "password"
        val loginParams = LogInUseCase.Params(email, password)
        val loginUseCase = mock<LogInUseCase>(){
            onBlocking { execute(loginParams) }.thenReturn(Unit)
        }
        viewModel = createViewModel(loginUseCase)
        viewModel.onLoginButtonClicked(email, password)
        val navigateTo = viewModel.navigationEvent.getValueForTesting()
        assertNotNull(navigateTo)
    }

    private fun createViewModel(
        logInUseCase: LogInUseCase = LogInUseCase(
            authRepository = mock()
        )
    ): LoginViewModel =
        LoginViewModel(
            logInUseCase
        )
}