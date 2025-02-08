package com.example.pdvapp.ui

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.viewmodel.CartViewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun CartScreen(
    onBack: () -> Unit = {}
) {
    // Obtenha o CartViewModel usando o escopo da atividade para compartilhar a mesma instância
    val cartViewModel: CartViewModel = viewModel(LocalContext.current as ComponentActivity)
    val cartItems = cartViewModel.cartItems

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Carrinho",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
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
                    // Exibe o nome do produto e a quantidade
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.produto.nome)
                        Text(text = "Qtd: ${item.quantidade}")
                    }
                    // Botão para remover o item do carrinho
                    Button(
                        onClick = { cartViewModel.removeItem(item.produto) }
                    ) {
                        Text("Remover")
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
                // Aqui você pode implementar a finalização da compra (checkout)
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
