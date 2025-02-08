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

@Composable
fun AddProductScreen(
    produtoViewModel: ProdutoViewModel = viewModel(),
    onProductAdded: () -> Unit = {}, // Callback para quando o produto for adicionado
    onCancel: () -> Unit = {}        // Callback para cancelar a operação
) {
    // Estados para os campos do formulário
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var codigoBarras by remember { mutableStateOf("") }
    var precoStr by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var quantidadeStr by remember { mutableStateOf("") }

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
        // Botão para adicionar o produto
        Button(
            onClick = {
                val preco = precoStr.toDoubleOrNull() ?: 0.0
                val quantidade = quantidadeStr.toIntOrNull() ?: 0

                val novoProduto = Produto(
                    nome = nome,
                    descricao = descricao,
                    codigoBarras = codigoBarras,
                    preco = preco,
                    categoria = categoria,
                    quantidadeEstoque = quantidade
                )

                produtoViewModel.inserir(novoProduto)

                // Limpa os campos (opcional)
                nome = ""
                descricao = ""
                codigoBarras = ""
                precoStr = ""
                categoria = ""
                quantidadeStr = ""

                onProductAdded()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Adicionar Produto")
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Botão para cancelar a operação e voltar
        Button(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Cancelar")
        }
    }
}
