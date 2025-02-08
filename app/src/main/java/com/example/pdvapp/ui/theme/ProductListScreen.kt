package com.example.pdvapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.pdvapp.viewmodel.SharedProductViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun ProductListScreen(
    onNavigateToAddProduct: () -> Unit = {},
    onProductClick: (Produto) -> Unit = {},
    sharedViewModel: SharedProductViewModel  // Novo parâmetro obrigatório
) {
    val produtoViewModel: ProdutoViewModel = viewModel()
    val produtos by produtoViewModel.produtos.observeAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAddProduct) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar Produto"
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            LazyColumn {
                items(produtos) { produto ->
                    Text(
                        text = produto.nome,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable {
                                sharedViewModel.selectProduct(produto)
                                onProductClick(produto)
                            }
                    )
                }
            }
        }
    }
}
