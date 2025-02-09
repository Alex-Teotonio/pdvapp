package com.example.pdvapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pdvapp.data.ticket.CartTicketItem
import com.example.pdvapp.data.ticket.Ingresso

class TicketCartViewModel : ViewModel() {
    private val _cartTickets = mutableStateListOf<CartTicketItem>()
    val cartTickets: List<CartTicketItem> get() = _cartTickets

    fun addTicket(ingresso: Ingresso, quantidade: Int = 1) {
        val existingItem = _cartTickets.find { it.ingresso.id == ingresso.id }
        if (existingItem != null) {
            existingItem.quantidade += quantidade
        } else {
            _cartTickets.add(CartTicketItem(ingresso, quantidade))
        }
    }

    fun updateTicketQuantity(ingresso: Ingresso, quantidade: Int) {
        val item = _cartTickets.find { it.ingresso.id == ingresso.id }
        if (item != null) {
            if (quantidade <= 0) {
                removeTicket(ingresso)
            } else {
                item.quantidade = quantidade
            }
        }
    }

    fun removeTicket(ingresso: Ingresso) {
        _cartTickets.removeAll { it.ingresso.id == ingresso.id }
    }

    fun clearCart() {
        _cartTickets.clear()
    }

    fun getTotalPrice(): Double {
        return _cartTickets.sumOf { it.ingresso.preco * it.quantidade }
    }
}
