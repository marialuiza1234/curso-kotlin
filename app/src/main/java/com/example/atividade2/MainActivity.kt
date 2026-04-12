package com.example.atividade2

import android.R.attr.icon
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.atividade2.ui.theme.Atividade2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Atividade2Theme {
                AppNavControleFinanceiro()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavControleFinanceiro() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Controle Financeiro")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(painter = painterResource(id = R.drawable.baseline_calculate_24),
                            contentDescription = null, modifier = Modifier.size(22.dp))
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("inicio")
                    },
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = null)
                    },
                    label = { Text("Início") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("ganhos")
                    },
                    icon = {
                        Icon(painter = painterResource(id = R.drawable.trofeu),
                            contentDescription = null, modifier = Modifier.size(22.dp))
                    },
                    label = { Text("Ganhos") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("gastos")
                    },
                    icon = {
                        Icon(painter = painterResource(id = R.drawable.perda_de_dinheiro),
                            contentDescription = null, modifier = Modifier.size(22.dp))
                    },
                    label = { Text("Gastos") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("sonhosEDesejos")
                    },
                    icon = {
                        Icon(painter = painterResource(id = R.drawable.lista_de_desejos),
                            contentDescription = null, modifier = Modifier.size(22.dp))
                    },
                    label = { Text("Objetivos") }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("inicio") { Inicio() }
            composable("ganhos") { Ganhos() }
            composable("gastos") { Gastos() }
            composable("sonhosEDesejos") { SonhosEDesejos() }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun Inicio() {
    val ganhos = remember {
        listOf(
            Ganho("Salário", 3500.00, "05/03/2026"),
            Ganho("Freelance", 800.00, "12/03/2026"),
            Ganho("Venda Online", 420.50, "25/03/2026")
        )
    }
    val gastos = remember {
        listOf(
            Gasto("Aluguel", 1200.00, "05/03/2026"),
            Gasto("Mercado", 450.00, "10/03/2026"),
            Gasto("Internet", 110.00, "15/03/2026")
        )
    }
    val transacoesOrdenadas: List<Triple<String, Double, String>> =
        mutableListOf<Triple<String, Double, String>>().apply {
            ganhos.forEach { add(Triple(it.nome, it.valor, it.data)) }
            gastos.forEach { add(Triple(it.nome, -it.valor, it.data)) }
        }
        .sortedByDescending { triple ->
            val p = triple.third.split("/")
            p[2] + p[1] + p[0]
        }

    val totalGanhos = ganhos.sumOf { it.valor }
    val totalGastos = gastos.sumOf { it.valor }
    val saldo = totalGanhos - totalGastos

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF4F6FA)).padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Olá, Maria Luiza",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.adeus),
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(150.dp))
            Icon(
                painter = painterResource(id = R.drawable.notificacao),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "10 de Abril, 2026",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        ResumoCard(
            titulo = "Saldo",
            valor = saldo,
            cor =
                if (saldo >= 0) {
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF42A5F5),
                            Color(0xFF1E88E5)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE53935),
                            Color(0xFFC62828)
                        )
                    )
                },
            fullWidth = true,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ResumoCard(
                titulo = "Ganhos",
                valor = totalGanhos,
                cor =
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF4CAF50),
                            Color(0xFF2E7D32)

                        )
                    )
            )
            ResumoCard(
                titulo = "Gastos",
                valor = totalGastos,
                cor =
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE53935),
                            Color(0xFFC62828)
                        )
                    )
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Últimas transações ----------------------",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontSize = 19.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Ver tudo",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Blue,
                fontSize = 13.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Expandir",
                tint = Color.Blue
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        transacoesOrdenadas.forEach { (descricao, valor, data) ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = descricao,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = data,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = if (valor >= 0)
                            "+ R$ %.2f".format(valor)
                        else
                            "- R$ %.2f".format(-valor),
                        color = if (valor >= 0)
                            Color(0xFF2E7D32)
                        else
                            Color(0xFFD32F2F),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ResumoCard(
    titulo: String,
    valor: Double,
    cor: Brush,
    fullWidth: Boolean = false
) {
    Card(
        modifier = Modifier.then(
            if (fullWidth) Modifier.fillMaxWidth()
            else Modifier.width(175.dp)
        ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(cor).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titulo,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = "R$ %.2f".format(valor),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class Ganho(
    val nome: String,
    val valor: Double,
    val data: String
)

@Composable
fun Ganhos(){
    val listaGanhos = rememberSaveable {
        mutableStateListOf(
            Ganho("Salário", 3500.00, "05/03/2026"),
            Ganho("Freelance", 800.00, "12/03/2026"),
            Ganho("Venda Online", 420.50, "25/03/2026")
        )
    }

    var nomeGanho by remember { mutableStateOf("") }
    var valorGanho by remember { mutableStateOf("") }
    var dataGanho by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFE8F5E9)).padding(16.dp),
    ){
        Text(
            text = "Meus Ganhos",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF2E7D32),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                OutlinedTextField(
                    value = nomeGanho,
                    onValueChange = { nomeGanho = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = valorGanho,
                    onValueChange = { valorGanho = it },
                    label = { Text("Valor (R$)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = dataGanho,
                    onValueChange = { dataGanho = it },
                    label = { Text("Data (dd/MM/yyyy)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        val valor = valorGanho.toDoubleOrNull()
                        if (nomeGanho.isNotBlank() && valor != null && dataGanho.isNotBlank()) {
                            listaGanhos.add(
                                Ganho(
                                    nome = nomeGanho,
                                    valor = valor,
                                    data = dataGanho
                                )
                            )
                            nomeGanho = ""
                            valorGanho = ""
                            dataGanho = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D32)
                    )
                ) {
                    Text("Adicionar ganho")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(listaGanhos) { ganho ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFC4E2C4)
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = ganho.nome,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF1B5E20)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "R$ %.2f".format(ganho.valor),
                            color = Color(0xFF2E7D32)
                        )
                        Text(
                            text = "Data: ${ganho.data}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}


data class Gasto(
    val nome: String,
    val valor: Double,
    val data: String
)

@Composable
fun Gastos() {
    val listaGastos = rememberSaveable {
        mutableStateListOf(
            Gasto("Aluguel", 1200.00, "05/03/2026"),
            Gasto("Mercado", 450.75, "10/03/2026"),
            Gasto("Internet", 110.00, "15/03/2026")
        )
    }
    var nomeGasto by remember { mutableStateOf("") }
    var valorGasto by remember { mutableStateOf("") }
    var dataGasto by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {

        Text(
            text = "Meus Gastos",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFFD32F2F),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                OutlinedTextField(
                    value = nomeGasto,
                    onValueChange = { nomeGasto = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = valorGasto,
                    onValueChange = { valorGasto = it },
                    label = { Text("Valor (R$)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = dataGasto,
                    onValueChange = { dataGasto = it },
                    label = { Text("Data (dd/MM/yyyy)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        val valor = valorGasto.toDoubleOrNull()
                        if (nomeGasto.isNotBlank() && valor != null && dataGasto.isNotBlank()) {
                            listaGastos.add(
                                Gasto(
                                    nome = nomeGasto,
                                    valor = valor,
                                    data = dataGasto
                                )
                            )
                            nomeGasto = ""
                            valorGasto = ""
                            dataGasto = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    )
                ) {
                    Text("Adicionar gasto")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(listaGastos) { gasto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEBEE)
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = gasto.nome,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFFB71C1C)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "R$ %.2f".format(gasto.valor),
                            color = Color(0xFFD32F2F)
                        )
                        Text(
                            text = "Data: ${gasto.data}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

data class Sonho(
    val nome: String,
    val valorTotal: Double,
    val valorAtual: Double,
    val imagem: Int
)

@Composable
fun SonhosEDesejos() {

    val listaSonhos = rememberSaveable {
        mutableStateListOf(
            Sonho("Viagem para Europa", 15000.00, 10000.00, R.drawable.europa),
            Sonho("Carro novo", 50000.00, 12000.00, R.drawable.carro),
            Sonho("Notebook", 8000.00, 3500.00, R.drawable.notebook)
        )
    }

    var nome by remember { mutableStateOf("") }
    var valorTotal by remember { mutableStateOf("") }
    var valorAtual by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .padding(16.dp)
    ) {
        Text(
            text = "Sonhos & Desejos",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF6A1B9A),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = valorTotal,
                    onValueChange = { valorTotal = it },
                    label = { Text("Meta (R$)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = valorAtual,
                    onValueChange = { valorAtual = it },
                    label = { Text("Valor guardado (R$)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.4f)

                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        val total = valorTotal.toDoubleOrNull()
                        val atual = valorAtual.toDoubleOrNull()

                        if (
                            nome.isNotBlank() &&
                            total != null &&
                            atual != null
                        ) {
                            listaSonhos.add(
                                Sonho(nome, total, atual, 0)
                            )
                            nome = ""
                            valorTotal = ""
                            valorAtual = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A1B9A)
                    )
                ) {
                    Text("Adicionar sonho")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(listaSonhos) { sonho ->
                val progresso =
                    (sonho.valorAtual / sonho.valorTotal).coerceIn(0.0, 1.0)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEED7E0)
                    ),
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = sonho.nome,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF6A1B9A)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Meta: R$ %.2f".format(sonho.valorTotal))
                        Text(text = "Guardado: R$ %.2f".format(sonho.valorAtual))
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { progresso.toFloat() },
                            color = Color(0xFF8E24AA),
                            trackColor = Color(0xFFE1BEE7),
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                }
            }
        }
    }
}