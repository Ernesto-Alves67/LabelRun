package com.scherzolambda.labelrun.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Lembre-se de substituir "SEU_ENDERECO_IP" pelo IP do seu servidor
    private const val BASE_URL = "http://SEU_ENDERECO_IP:8080/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}
