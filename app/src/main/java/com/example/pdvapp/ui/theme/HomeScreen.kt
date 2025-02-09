package com.example.pdvapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onProductsClick: () -> Unit,
    onTicketsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título da empresa
        Text(
            text = "Maax Soluções",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF014029)
        )
        Text(
            text = "Tecnologia em eventos",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF0D0D0D), // Usando uma cor escura para contraste
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Card para Produtos
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onProductsClick() }
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF014029) // primary: verde escuro
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Produtos",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }
        }
        // Card para Ingressos
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onTicketsClick() }
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1DF2F2) // secondary: ciano
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ingressos",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black
                )
            }
        }
    }
}
