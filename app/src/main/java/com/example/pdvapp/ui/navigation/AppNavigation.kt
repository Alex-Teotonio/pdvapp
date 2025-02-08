package com.example.pdvapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pdvapp.ui.theme.ProductListScreen
import com.example.pdvapp.ui.theme.ProductDetailScreen
import com.example.pdvapp.ui.theme.AddProductScreen
import com.example.pdvapp.ui.theme.CartScreen
import com.example.pdvapp.viewmodel.SharedProductViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedProductViewModel = viewModel()

    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(
                onNavigateToAddProduct = { navController.navigate("addProduct") },
                onProductClick = { produto ->
                    sharedViewModel.selectProduct(produto)
                    navController.navigate("productDetail")
                },
                sharedViewModel = sharedViewModel,
                onNavigateToCart = { navController.navigate("cart") }  // Callback para navegar ao carrinho
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
