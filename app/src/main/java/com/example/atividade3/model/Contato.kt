package com.example.atividade3.model

data class Contato (
    val id: Int = 0,
    val nome: String,
    val email: String,
    val telefone: String,
    val nascimento: String,
    val cep: String,
    val bairro: String,
    val logradouro: String,
    val numero: String,
    val cidade: String,
)