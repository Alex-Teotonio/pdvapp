package com.example.pdvapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.pdvapp.data.local.Produto
import com.example.pdvapp.viewmodel.ProdutoViewModel

@Composable
fun ProductListScreen() {
    // Obtenha a instância do ViewModel usando a função viewModel() do Compose
    val produtoViewModel: ProdutoViewModel = viewModel()

    // Observe o LiveData dos produtos e converta para um estado Compose
    val produtos by produtoViewModel.produtos.observeAsState(initial = emptyList())

    // Estrutura da tela: Coluna com um botão para inserir um produto de teste e a lista de produtos
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Button(onClick = {
                // Cria um produto de teste
                val testProduct = Produto(
                    nome = "Test Product",
                    descricao = "Test description",
                    codigoBarras = "1234567890",
                    preco = 9.99,
                    categoria = "Test Category",
                    quantidadeEstoque = 100
                )
                // Insere o produto de teste
                produtoViewModel.inserir(testProduct)
            }) {
                Text("Add Test Product")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(produtos) { produto ->
                    // Exibe o nome do produto (você pode personalizar essa UI conforme desejar)
                    Text(
                        text = produto.nome,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}
