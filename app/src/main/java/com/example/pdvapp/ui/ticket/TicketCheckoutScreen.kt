package com.example.pdvapp.ui.ticket

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.viewmodel.TicketCartViewModel

@Composable
fun TicketCheckoutScreen(
    onCheckoutComplete: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val ticketCartViewModel: TicketCartViewModel = viewModel(LocalContext.current as ComponentActivity)
    val cartTickets = ticketCartViewModel.cartTickets
    val total = ticketCartViewModel.getTotalPrice()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Resumo da Compra de Ingressos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartTickets) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item.ingresso.tipo, modifier = Modifier.weight(1f))
                        Text(text = "Qtd: ${item.quantidade}")
                        Text(
                            text = "Subtotal: R$ ${"%.2f".format(item.ingresso.preco * item.quantidade)}",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total: R$ ${"%.2f".format(total)}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    ticketCartViewModel.clearCart()
                    onCheckoutComplete()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar Compra")
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
}
