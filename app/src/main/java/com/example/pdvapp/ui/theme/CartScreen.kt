package com.example.pdvapp.ui

import androidx.compose.foundation.layout.*
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
import androidx.activity.ComponentActivity
@Composable
fun CartScreen(
    onBack: () -> Unit = {}
) {
    // Obtenha o CartViewModel; para compartilhar entre telas, você pode usar o escopo da atividade
    val cartViewModel: CartViewModel = viewModel(LocalContext.current as ComponentActivity)
    val cartItems = cartViewModel.cartItems

    Column(modifier = Modifier
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
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = item.produto.nome,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "Qtd: ${item.quantidade}")
                    // Você pode adicionar botões para incrementar ou remover aqui
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
                // Exemplo: finalizar compra (aqui você pode implementar o checkout)
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
