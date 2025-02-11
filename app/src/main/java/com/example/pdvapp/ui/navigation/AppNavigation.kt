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
import com.example.pdvapp.ui.theme.HomeScreen
import com.example.pdvapp.ui.theme.CartScreen
import com.example.pdvapp.ui.theme.CheckoutScreen
import com.example.pdvapp.viewmodel.SharedProductViewModel
import com.example.pdvapp.viewmodel.CartViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Obtenha o SharedProductViewModel (para compartilhar o produto selecionado)
    val sharedViewModel: SharedProductViewModel = viewModel()
    // Obtenha o CartViewModel do escopo da atividade
    val cartViewModel: CartViewModel = viewModel(LocalContext.current as ComponentActivity)

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onProductsClick = { navController.navigate("productList") },
                onTicketsClick = { navController.navigate("ticketFlow") } // Supondo um fluxo de ingressos
            )
        }
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
                onBack = { navController.popBackStack() },
                onNavigateToCheckout = { navController.navigate("checkout") }
            )
        }
        composable("checkout") {
            CheckoutScreen(
                onCheckoutComplete = {
                    // Após finalizar a compra, navegue de volta à lista
                    navController.navigate("productList") {
                        popUpTo("productList") { inclusive = true }
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
