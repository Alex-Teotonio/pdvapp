package com.example.pdvapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.data.local.Produto
import com.example.pdvapp.viewmodel.ProdutoViewModel
import com.example.pdvapp.viewmodel.SharedProductViewModel

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
                    ProductItem(
                        produto = produto,
                        onProductClick = onProductClick,
                        onAddToCart = onAddToCart,
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    produto: Produto,
    onProductClick: (Produto) -> Unit,
    onAddToCart: (Produto) -> Unit,
    sharedViewModel: SharedProductViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                sharedViewModel.selectProduct(produto)
                onProductClick(produto)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = produto.nome,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = produto.descricao ?: "Descrição não disponível",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${produto.preco}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
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