package com.example.pdvapp.ui.theme

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.viewmodel.CartViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onBack: () -> Unit = {}
) {
    val cartViewModel: CartViewModel = viewModel(LocalContext.current as ComponentActivity)
    val cartItems = cartViewModel.cartItems

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrinho") })
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
                items(cartItems) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.produto.nome, style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Preço: R$ ${item.produto.preco}", style = MaterialTheme.typography.bodyMedium)
                        }
                        Row(
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { cartViewModel.decrementItem(item.produto) }) {
                                Icon(imageVector = Icons.Default.Remove, contentDescription = "Diminuir Quantidade")
                            }
                            Text(
                                text = "${item.quantidade.value}",
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            IconButton(onClick = { cartViewModel.incrementItem(item.produto) }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Aumentar Quantidade")
                            }
                        }
                        IconButton(onClick = { cartViewModel.removeItem(item.produto) }) {
                            Icon(imageVector = Icons.Default.ClearAll, contentDescription = "Remover Item")
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
                onClick = {
                    // Exemplo: finalizar compra
                    cartViewModel.clearCart()
                },
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
