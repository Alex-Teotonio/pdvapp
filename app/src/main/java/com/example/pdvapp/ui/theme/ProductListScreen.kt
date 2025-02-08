package com.example.pdvapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.pdvapp.data.local.Produto
import com.example.pdvapp.viewmodel.ProdutoViewModel
import com.example.pdvapp.viewmodel.SharedProductViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    onNavigateToAddProduct: () -> Unit = {},
    onProductClick: (Produto) -> Unit = {},
    onAddToCart: (Produto) -> Unit = {},
    sharedViewModel: SharedProductViewModel
) {
    val produtoViewModel: ProdutoViewModel = viewModel()
    val produtos by produtoViewModel.produtos.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Produtos") },
                actions = {
                    IconButton(onClick = { /* Aqui pode ser outro callback para ir ao carrinho */ }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrinho"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAddProduct) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar Produto"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            LazyColumn {
                items(produtos) { produto ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Nome do produto; ao clicar, navega para a tela de detalhes
                        Text(
                            text = produto.nome,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    sharedViewModel.selectProduct(produto)
                                    onProductClick(produto)
                                }
                        )
                        // Ícone de carrinho para adicionar o produto e ir para o carrinho
                        IconButton(onClick = { onAddToCart(produto) }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Adicionar ao Carrinho"
                            )
                        }
                    }
                }
            }
        }
    }
}
