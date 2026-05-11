package com.example.atividade3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.atividade3.model.Contato
import com.example.atividade3.network.ViaCepApi
import com.example.atividade3.network.ViaCepResponse
import com.example.atividade3.repository.ContatoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContatoViewModel : ViewModel(){
    private val repository = ContatoRepository()
    val listaContatos = repository.listarContatos()
    private val retrofit = Retrofit.Builder().baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val api = retrofit.create(ViaCepApi::class.java)

    fun salvar(contato: Contato){
        repository.inserirContato(contato)
    }

    fun remover(contato: Contato){
        repository.removerContato(contato)
    }

    fun editar(contato: Contato){
        repository.atualizarContato(contato)
    }

    suspend fun buscaCep(cep: String): ViaCepResponse{
        return api.buscarCep(cep)
    }
}