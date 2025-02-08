package com.example.pdvapp.ui.theme

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.data.local.Produto
import com.example.pdvapp.viewmodel.ProdutoViewModel
import com.example.pdvapp.viewmodel.SharedProductViewModel

@Composable
fun ProductDetailScreen(
    sharedViewModel: SharedProductViewModel, // Remova o valor padrão para garantir a passagem correta
    onBack: () -> Unit = {}
) {
    val produtoViewModel: ProdutoViewModel = viewModel()
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
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = codigoBarras,
                onValueChange = { codigoBarras = it },
                label = { Text("Código de Barras") },
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
                value = categoria,
                onValueChange = { categoria = it },
                label = { Text("Categoria") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = quantidadeStr,
                onValueChange = { quantidadeStr = it },
                label = { Text("Quantidade em Estoque") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            Button(
                onClick = {
                    produtoViewModel.excluir(produto)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Excluir Produto")
            }
        }
    }
}
