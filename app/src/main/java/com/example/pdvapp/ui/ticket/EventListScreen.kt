package com.example.pdvapp.ui.ticket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pdvapp.data.ticket.Evento

@Composable
fun EventListScreen(
    events: List<Evento>,
    onEventSelected: (Evento) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {
        items(events) { event ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onEventSelected(event) },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = event.nome,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Data: ${event.data}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Local: ${event.local}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
