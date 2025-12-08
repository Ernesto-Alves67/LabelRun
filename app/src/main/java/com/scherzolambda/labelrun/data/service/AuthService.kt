package com.scherzolambda.labelrun.data.service

import com.scherzolambda.labelrun.data.model.DefaultResponse
import com.scherzolambda.labelrun.data.model.LoginRequest
import com.scherzolambda.labelrun.data.model.LoginResponse
import com.scherzolambda.labelrun.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<DefaultResponse>

    // Adicione outros endpoints da sua API aqui
}