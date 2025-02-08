package com.example.pdvapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pdvapp.ui.theme.ProductListScreen
import com.example.pdvapp.ui.theme.ProductDetailScreen
import com.example.pdvapp.ui.theme.AddProductScreen
import com.example.pdvapp.ui.theme.CartScreen
import com.example.pdvapp.viewmodel.SharedProductViewModel
import com.example.pdvapp.viewmodel.CartViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Obtenha o SharedProductViewModel para compartilhar entre as telas
    val sharedViewModel: SharedProductViewModel = viewModel()
    // Obtenha o CartViewModel do escopo da atividade para compartilhar a mesma instância
    val cartViewModel: CartViewModel = viewModel(LocalContext.current as ComponentActivity)

    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(
                onNavigateToAddProduct = { navController.navigate("addProduct") },
                onProductClick = { produto ->
                    sharedViewModel.selectProduct(produto)
                    navController.navigate("productDetail")
                },
                onAddToCart = { produto ->
                    cartViewModel.addItem(produto)
                    navController.navigate("cart")
                },
                sharedViewModel = sharedViewModel
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
        composable("cart") {
            CartScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
