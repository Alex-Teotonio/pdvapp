package com.example.pdvapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pdvapp.data.local.CartItem
import com.example.pdvapp.data.local.Produto
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addItem(produto: Produto, quantidade: Int = 1) {
        val existingItem = _cartItems.find { it.produto.id == produto.id }
        if (existingItem != null) {
            existingItem.quantidade.value += quantidade
        } else {
            _cartItems.add(CartItem(produto, mutableStateOf(quantidade)))
        }
    }

    fun incrementItem(produto: Produto) {
        val item = _cartItems.find { it.produto.id == produto.id }
        if (item != null) {
            item.quantidade.value += 1
        } else {
            _cartItems.add(CartItem(produto, mutableStateOf(1)))
        }
    }

    fun decrementItem(produto: Produto) {
        val item = _cartItems.find { it.produto.id == produto.id }
        if (item != null) {
            if (item.quantidade.value > 1) {
                item.quantidade.value -= 1
            } else {
                removeItem(produto)
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
        return _cartItems.sumOf { it.produto.preco * it.quantidade.value }
    }
}
