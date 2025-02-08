package com.example.pdvapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.ui.theme.ProductListScreen
import com.example.pdvapp.ui.theme.ProductDetailScreen
import com.example.pdvapp.ui.theme.AddProductScreen
import com.example.pdvapp.viewmodel.SharedProductViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current as ComponentActivity
    // Obtenha o SharedProductViewModel do escopo da atividade
    val sharedViewModel: SharedProductViewModel = viewModel(context)

    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(
                onNavigateToAddProduct = { navController.navigate("addProduct") },
                onProductClick = { produto ->
                    // Armazene o produto selecionado no sharedViewModel
                    sharedViewModel.selectProduct(produto)
                    navController.navigate("productDetail")
                },
                sharedViewModel = sharedViewModel // Passe a instância compartilhada
            )
        }
        composable("addProduct") {
            AddProductScreen(
                onProductAdded = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
        composable("productDetail") {
            ProductDetailScreen(
                sharedViewModel = sharedViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
