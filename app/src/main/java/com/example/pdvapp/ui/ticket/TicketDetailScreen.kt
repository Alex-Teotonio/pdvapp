package com.example.pdvapp.ui.ticket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pdvapp.data.ticket.Evento
import com.example.pdvapp.data.ticket.Ingresso
import androidx.compose.foundation.layout.height


@Composable
fun TicketDetailScreen(
    event: Evento,
    availableTickets: List<Ingresso>,
    onAddTicket: (Ingresso) -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = event.nome, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Data: ${event.data}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Local: ${event.local}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Ingressos Disponíveis:", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(availableTickets) { ingresso ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Tipo: ${ingresso.tipo}", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Preço: R$ ${ingresso.preco}", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { onAddTicket(ingresso) }) {
                            Text("Adicionar ao Carrinho")
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
