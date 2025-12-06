package com.scherzolambda.labelrun.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scherzolambda.labelrun.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gerenciar o estado da autenticação
 * @param authRepository Repositório de autenticação (injetável para testes)
 */
class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    /**
     * Realiza login do usuário
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            val result = authRepository.login(email, password)
            _loginState.value = if (result.isSuccess) {
                val token = result.getOrNull()?.token
                LoginState.Success(token ?: "")
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Erro desconhecido")
            }
        }
    }

    /**
     * Realiza registro de novo usuário
     */
    fun register(email: String, password: String, username: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading

            val result = authRepository.register(email, password, username)
            _registerState.value = if (result.isSuccess) {
                val message = result.getOrNull()?.message
                RegisterState.Success(message ?: "Usuário registrado com sucesso")
            } else {
                RegisterState.Error(result.exceptionOrNull()?.message ?: "Erro desconhecido")
            }
        }
    }

    /**
     * Reseta o estado de login
     */
    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }

    /**
     * Reseta o estado de registro
     */
    fun resetRegisterState() {
        _registerState.value = RegisterState.Idle
    }
}

/**
 * Estados possíveis para o login
 */
sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

/**
 * Estados possíveis para o registro
 */
sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data class Success(val message: String) : RegisterState()
    data class Error(val message: String) : RegisterState()
}
