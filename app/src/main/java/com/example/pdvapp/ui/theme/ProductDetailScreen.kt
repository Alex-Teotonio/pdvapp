package com.example.pdvapp.ui.theme

import androidx.compose.foundation.layout.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.data.local.Produto
import com.example.pdvapp.viewmodel.ProdutoViewModel
import com.example.pdvapp.viewmodel.SharedProductViewModel
import com.example.pdvapp.viewmodel.CartViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
@Composable
fun ProductDetailScreen(
    sharedViewModel: SharedProductViewModel,
    onBack: () -> Unit = {}
) {
    val produtoViewModel: ProdutoViewModel = viewModel()
    // Obtenha o CartViewModel do escopo da atividade
    val cartViewModel: CartViewModel = viewModel(LocalContext.current as ComponentActivity)
    val produto = sharedViewModel.selectedProduct.value

    if (produto == null) {
        Text("Nenhum produto selecionado")
        onBack()
    } else {
        var nome by remember { mutableStateOf(produto.nome) }
        var descricao by remember { mutableStateOf(produto.descricao) }
        var codigoBarras by remember { mutableStateOf(produto.codigoBarras) }
        var precoStr by remember { mutableStateOf(produto.preco.toString()) }
        var categoria by remember { mutableStateOf(produto.categoria) }
        var quantidadeStr by remember { mutableStateOf(produto.quantidadeEstoque.toString()) }

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )
            // ... outros campos de edição ...
            Spacer(modifier = Modifier.height(16.dp))
            // Botão para atualizar
            Button(
                onClick = {
                    val preco = precoStr.toDoubleOrNull() ?: 0.0
                    val quantidade = quantidadeStr.toIntOrNull() ?: 0

                    val produtoAtualizado = produto.copy(
                        nome = nome,
                        descricao = descricao,
                        codigoBarras = codigoBarras,
                        preco = preco,
                        categoria = categoria,
                        quantidadeEstoque = quantidade
                    )

                    produtoViewModel.atualizar(produtoAtualizado)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Atualizar Produto")
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Botão para excluir
            Button(
                onClick = {
                    produtoViewModel.excluir(produto)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Excluir Produto")
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Botão para adicionar ao carrinho
            Button(
                onClick = {
                    cartViewModel.addItem(produto)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Adicionar ao Carrinho")
            }
        }
    }
}
