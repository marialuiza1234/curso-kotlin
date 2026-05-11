package com.example.atividade3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.atividade3.ui.theme.screens.CadastroContatoScreen
import com.example.atividade3.ui.theme.screens.ListaContatoScreen
import com.example.atividade3.viewmodel.ContatoViewModel
import com.example.atividade3.model.Contato

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel: ContatoViewModel = viewModel()
                var tela by rememberSaveable { mutableStateOf("lista") }
                var contatoEmEdicao by rememberSaveable {
                    mutableStateOf<Contato?>(null)
                }
                when (tela) {
                    "lista" -> ListaContatoScreen(
                        viewModel = viewModel,
                        onAdicionar = {
                            contatoEmEdicao = null
                            tela = "cadastro"
                        },
                        onEditar = { contato ->
                            contatoEmEdicao = contato
                            tela = "cadastro"
                        }
                    )
                    "cadastro" -> CadastroContatoScreen(
                        viewModel = viewModel,
                        contato = contatoEmEdicao,
                        onSalvar = { tela = "lista" }
                    )
                }
            }
        }
    }
}
