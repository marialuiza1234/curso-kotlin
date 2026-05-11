package com.example.atividade3.repository

import com.example.atividade3.model.Contato
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContatoRepository {
    private val _contatos = MutableStateFlow<List<Contato>>(emptyList())
    val contatos: StateFlow<List<Contato>> = _contatos

    fun listarContatos(): StateFlow<List<Contato>> = contatos

    fun inserirContato(contato: Contato) {
        _contatos.value = _contatos.value + contato
    }

    fun removerContato(contato: Contato) {
        _contatos.value = _contatos.value - contato
    }

    fun atualizarContato(contato: Contato) {
        _contatos.value = _contatos.value.map {
            if (it.id == contato.id) contato else it
        }
    }
}