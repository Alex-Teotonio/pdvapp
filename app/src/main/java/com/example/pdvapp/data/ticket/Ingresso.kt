package com.example.pdvapp.data.ticket

data class Ingresso(
    val id: Long,
    val eventoId: Long,      // Relacionamento com o Evento
    val tipo: String,        // Ex.: "VIP", "Geral"
    val preco: Double,
    val disponibilidade: Int // Quantidade disponível para venda
)
