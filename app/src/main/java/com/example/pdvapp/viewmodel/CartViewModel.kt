package com.example.pdvapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pdvapp.data.local.CartItem
import com.example.pdvapp.data.local.Produto

class CartViewModel : ViewModel() {
    // Lista mutável para armazenar os itens do carrinho
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addItem(produto: Produto, quantidade: Int = 1) {
        // Se o produto já estiver no carrinho, atualize a quantidade
        val existingItem = _cartItems.find { it.produto.id == produto.id }
        if (existingItem != null) {
            existingItem.quantidade += quantidade
        } else {
            _cartItems.add(CartItem(produto, quantidade))
        }
    }

    fun updateQuantity(produto: Produto, quantidade: Int) {
        val item = _cartItems.find { it.produto.id == produto.id }
        if (item != null) {
            if (quantidade <= 0) {
                removeItem(produto)
            } else {
                item.quantidade = quantidade
            }
        }
    }

    fun removeItem(produto: Produto) {
        _cartItems.removeAll { it.produto.id == produto.id }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotalPrice(): Double {
        return _cartItems.sumOf { it.produto.preco * it.quantidade }
    }
}
