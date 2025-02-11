package com.example.pdvapp.ui.ticket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pdvapp.data.ticket.Ingresso

@Composable
fun TicketRegistrationScreen(
    onTicketRegistered: (Ingresso) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    var eventoIdStr by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var precoStr by remember { mutableStateOf("") }
    var disponibilidadeStr by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = eventoIdStr,
            onValueChange = { eventoIdStr = it },
            label = { Text("ID do Evento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo de Ingresso") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = precoStr,
            onValueChange = { precoStr = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = disponibilidadeStr,
            onValueChange = { disponibilidadeStr = it },
            label = { Text("Disponibilidade") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Converte os valores dos campos para os tipos adequados
                val eventoId = eventoIdStr.toLongOrNull() ?: 0L
                val preco = precoStr.toDoubleOrNull() ?: 0.0
                val disponibilidade = disponibilidadeStr.toIntOrNull() ?: 0

                val ingresso = Ingresso(
                    id = 0,
                    eventoId = eventoId,
                    tipo = tipo,
                    preco = preco,
                    disponibilidade = disponibilidade
                )
                onTicketRegistered(ingresso)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar Ingresso")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}
