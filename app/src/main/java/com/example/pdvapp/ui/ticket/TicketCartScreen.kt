package com.example.pdvapp.ui.ticket

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.viewmodel.TicketCartViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material3.ExperimentalMaterial3Api


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketCartScreen(
    onBack: () -> Unit = {},
    onNavigateToCheckout: () -> Unit = {}
) {
    // Obtenha o TicketCartViewModel do escopo da atividade
    val cartViewModel: TicketCartViewModel = viewModel(LocalContext.current as ComponentActivity)
    val cartTickets = cartViewModel.cartTickets

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrinho de Ingressos") })
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartTickets) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.ingresso.tipo, style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Preço: R$ ${item.ingresso.preco}", style = MaterialTheme.typography.bodyMedium)
                        }
                        Row(
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { cartViewModel.updateTicketQuantity(item.ingresso, item.quantidade - 1) }) {
                                Icon(imageVector = Icons.Default.Remove, contentDescription = "Diminuir Quantidade")
                            }
                            Text(text = "${item.quantidade}", modifier = Modifier.padding(horizontal = 8.dp))
                            IconButton(onClick = { cartViewModel.updateTicketQuantity(item.ingresso, item.quantidade + 1) }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Aumentar Quantidade")
                            }
                        }
                        IconButton(onClick = { cartViewModel.removeTicket(item.ingresso) }) {
                            Icon(imageVector = Icons.Default.ClearAll, contentDescription = "Remover Ingresso")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total: R$ ${cartViewModel.getTotalPrice()}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onNavigateToCheckout,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar Compra")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Voltar")
            }
        }
    }
}
