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
import com.example.pdvapp.ui.ticket.EventListScreen
import com.example.pdvapp.ui.ticket.TicketDetailScreen
import com.example.pdvapp.ui.ticket.TicketCartScreen
import com.example.pdvapp.ui.ticket.TicketCheckoutScreen
import com.example.pdvapp.viewmodel.SharedProductViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Obtenha os ViewModels compartilhados conforme necessário
    val sharedProductViewModel: SharedProductViewModel = viewModel()
    // Para o fluxo de ingressos, usaremos o TicketCartViewModel (obtido nas telas via LocalContext)

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onProductsClick = { navController.navigate("productList") },
                onTicketsClick = { navController.navigate("eventList") }
            )
        }
        // Rotas para produtos (já implementadas)
        composable("productList") {
            ProductListScreen(
                onNavigateToAddProduct = { navController.navigate("addProduct") },
                onProductClick = { produto ->
                    sharedProductViewModel.selectProduct(produto)
                    navController.navigate("productDetail")
                },
                onAddToCart = { produto ->
                    // Produto relacionado ao fluxo de produtos
                    navController.navigate("cart")
                },
                sharedViewModel = sharedProductViewModel
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
                sharedViewModel = sharedProductViewModel,
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
                    navController.navigate("productList") {
                        popUpTo("productList") { inclusive = true }
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }
        // Fluxo de ingressos
        composable("eventList") {
            // Simule uma lista de eventos
            val events = listOf(
                com.example.pdvapp.data.ticket.Evento(1, "Concerto de Rock", "2025-05-10", "Estádio A", "Um grande concerto!"),
                com.example.pdvapp.data.ticket.Evento(2, "Festival de Jazz", "2025-06-15", "Parque B", "Um festival imperdível!")
            )
            com.example.pdvapp.ui.ticket.EventListScreen(
                events = events,
                onEventSelected = { event ->
                    // Navegue para a tela de detalhes do ingresso para o evento selecionado
                    navController.navigate("ticketDetail/${event.id}")
                }
            )
        }
        composable("ticketDetail/{eventId}") { backStackEntry ->
            // Para simplicidade, recupere o eventId dos argumentos e simule ingressos
            val eventId = backStackEntry.arguments?.getString("eventId")?.toLongOrNull() ?: 0L
            // Simule o evento com base no ID (em um cenário real, você buscaria o evento no repositório)
            val event = com.example.pdvapp.data.ticket.Evento(eventId, "Evento $eventId", "2025-07-20", "Local X", "Descrição do evento")
            // Simule uma lista de ingressos para esse evento
            val availableTickets = listOf(
                com.example.pdvapp.data.ticket.Ingresso(1, eventId, "VIP", 150.0, 50),
                com.example.pdvapp.data.ticket.Ingresso(2, eventId, "Geral", 80.0, 200)
            )
            com.example.pdvapp.ui.ticket.TicketDetailScreen(
                event = event,
                availableTickets = availableTickets,
                onAddTicket = { ingresso ->
                    // Adicione o ingresso ao carrinho de ingressos (o TicketCartViewModel será obtido na tela)
                    navController.navigate("ticketCart")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable("ticketCart") {
            com.example.pdvapp.ui.ticket.TicketCartScreen(
                onBack = { navController.popBackStack() },
                onNavigateToCheckout = { navController.navigate("ticketCheckout") }
            )
        }
        composable("ticketCheckout") {
            TicketCheckoutScreen(
                onCheckoutComplete = {
                    navController.navigate("eventList") {
                        popUpTo("eventList") { inclusive = true }
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
