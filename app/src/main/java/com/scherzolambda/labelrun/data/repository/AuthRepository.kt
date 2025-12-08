package com.scherzolambda.labelrun.data.repository

import android.util.Log
import com.scherzolambda.labelrun.core.network.ApiClient
import com.scherzolambda.labelrun.data.model.DefaultResponse
import com.scherzolambda.labelrun.data.model.LoginRequest
import com.scherzolambda.labelrun.data.model.LoginResponse
import com.scherzolambda.labelrun.data.model.RegisterRequest
import com.scherzolambda.labelrun.data.service.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository para gerenciar operações de autenticação com a API
 */
class AuthRepository {
    private val apiService = ApiClient.getRetrofitInstance().create(AuthService::class.java)
    private val TAG = "AuthRepository"

    /**
     * Realiza login do usuário
     * @param email Email do usuário
     * @param password Senha do usuário
     * @return Result contendo LoginResponse em caso de sucesso ou Exception em caso de erro
     */
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = LoginRequest(email = email, pass = password)
                val response = apiService.login(request)

                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "Login bem-sucedido para: $email")
                    Result.success(response.body()!!)
                } else {
                    val errorMessage = when (response.code()) {
                        401 -> "Email ou senha inválidos"
                        404 -> "Usuário não encontrado"
                        500 -> "Erro no servidor. Tente novamente mais tarde"
                        else -> "Erro ao fazer login: ${response.code()}"
                    }
                    Log.e(TAG, "Erro no login: $errorMessage (código: ${response.code()})")
                    Result.failure(Exception(errorMessage))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao fazer login", e)
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "Não foi possível conectar ao servidor. Verifique sua conexão"
                    is java.net.SocketTimeoutException -> "Tempo de conexão esgotado. Tente novamente"
                    is java.net.ConnectException -> "Falha ao conectar com o servidor"
                    else -> "Erro ao fazer login: ${e.message}"
                }
                Result.failure(Exception(errorMessage))
            }
        }
    }

    /**
     * Realiza registro de novo usuário
     * @param email Email do usuário
     * @param password Senha do usuário
     * @param username Nome de usuário
     * @return Result contendo DefaultResponse em caso de sucesso ou Exception em caso de erro
     */
    suspend fun register(email: String, password: String, username: String): Result<DefaultResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = RegisterRequest(username = username, email = email, pass = password)
                val response = apiService.register(request)

                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "Registro bem-sucedido para: $email")
                    Result.success(response.body()!!)
                } else {
                    val errorMessage = when (response.code()) {
                        409 -> "Este email já está cadastrado"
                        400 -> "Dados inválidos. Verifique os campos"
                        500 -> "Erro no servidor. Tente novamente mais tarde"
                        else -> "Erro ao registrar: ${response.code()}"
                    }
                    Log.e(TAG, "Erro no registro: $errorMessage (código: ${response.code()})")
                    Result.failure(Exception(errorMessage))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao registrar", e)
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "Não foi possível conectar ao servidor. Verifique sua conexão"
                    is java.net.SocketTimeoutException -> "Tempo de conexão esgotado. Tente novamente"
                    is java.net.ConnectException -> "Falha ao conectar com o servidor"
                    else -> "Erro ao registrar: ${e.message}"
                }
                Result.failure(Exception(errorMessage))
            }
        }
    }
}
