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
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.viewmodel.CartViewModel

@Composable
fun CheckoutScreen(
    onCheckoutComplete: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val cartViewModel: CartViewModel = viewModel(LocalContext.current as ComponentActivity)
    val cartItems = cartViewModel.cartItems
    val total = cartViewModel.getTotalPrice()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Resumo da Compra",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.produto.nome,
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = "Qtd: ${item.quantidade.value}")
                        Text(text = "Subtotal: R$ ${"%.2f".format(item.produto.preco * item.quantidade.value)}")
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
                    // Simula o checkout: você pode integrar com um sistema de pagamento aqui
                    cartViewModel.clearCart()
                    onCheckoutComplete() // Callback para notificar que o checkout foi concluído
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar Compra")
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
