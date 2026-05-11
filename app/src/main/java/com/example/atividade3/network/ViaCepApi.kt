package com.example.atividade3.network

import retrofit2.http.Path
import retrofit2.http.GET

interface ViaCepApi {
    @GET("{cep}/json")
    suspend fun buscarCep(@Path("cep") cep: String): ViaCepResponse
}