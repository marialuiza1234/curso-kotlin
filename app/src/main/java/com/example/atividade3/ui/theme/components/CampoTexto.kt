package com.example.atividade3.ui.theme.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.OutlinedTextField

@Composable
fun CampoTexto(valor: String, label: String, onValueChange: (String) -> Unit){
    OutlinedTextField(value = valor,
                      onValueChange =  onValueChange,
                      label = {
                          Text(text = label)
                      }
    )
}