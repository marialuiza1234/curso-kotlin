package com.example.atividade3.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.atividade3.model.Contato
import com.example.atividade3.ui.theme.components.CampoTexto
import com.example.atividade3.viewmodel.ContatoViewModel
import kotlinx.coroutines.launch

@Composable
fun CadastroContatoScreen(viewModel: ContatoViewModel = viewModel(), contato: Contato?, onSalvar: () -> Unit) {
    val scope = rememberCoroutineScope()
    var nome by remember { mutableStateOf(contato?.nome ?: "") }
    var email by remember { mutableStateOf(contato?.email ?: "") }
    var telefone by remember { mutableStateOf(contato?.telefone ?: "") }
    var nascimento by remember { mutableStateOf(contato?.nascimento ?: "") }
    var cep by remember { mutableStateOf(contato?.cep ?: "") }
    var bairro by remember { mutableStateOf(contato?.bairro ?: "") }
    var logradouro by remember { mutableStateOf(contato?.logradouro ?: "") }
    var numero by remember { mutableStateOf(contato?.numero ?: "") }
    var cidade by remember { mutableStateOf(contato?.cidade ?: "") }


    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Cadastro de Contato",
            style = MaterialTheme.typography.headlineMedium
        )
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Dados pessoais",
                    style = MaterialTheme.typography.titleMedium
                )
                CampoTexto(nome, "Nome") { nome = it }
                CampoTexto(email, "E-mail") { email = it }
                CampoTexto(telefone, "Telefone") { telefone = it }
                CampoTexto(nascimento, "Nascimento") { nascimento = it }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Endereço",
                    style = MaterialTheme.typography.titleMedium
                )
                CampoTexto(cep, "CEP") {
                    cep = it
                    if (it.length == 8) {
                        scope.launch {
                            val endereco = viewModel.buscaCep(it)
                            bairro = endereco.bairro
                            logradouro = endereco.logradouro
                            cidade = endereco.localidade 
                        }
                    }
                }
                CampoTexto(logradouro, "Logradouro") { logradouro = it }
                CampoTexto(numero, "Número") { numero = it }
                CampoTexto(bairro, "Bairro") { bairro = it }
                CampoTexto(cidade, "Cidade") { cidade = it }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedButton(
                onClick = { onSalvar() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Voltar")
            }
            Button(onClick = {
                val contatoFinal = Contato(
                    id = contato?.id ?: (0..9999).random(),
                    nome = nome,
                    email = email,
                    telefone = telefone,
                    nascimento = nascimento,
                    cep = cep,
                    bairro = bairro,
                    logradouro = logradouro,
                    numero = numero,
                    cidade = cidade
                )
                if (contato != null) {
                    viewModel.editar(contatoFinal)
                } else {
                    viewModel.salvar(contatoFinal)
                }
                onSalvar()
            }) {
                Text(if (contato != null) "Atualizar" else "Salvar")
            }
        }
    }
}