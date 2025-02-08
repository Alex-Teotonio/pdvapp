package com.example.pdvapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pdvapp.data.local.Produto

class SharedProductViewModel : ViewModel() {
    // Armazena o produto selecionado
    var selectedProduct = mutableStateOf<Produto?>(null)
        private set

    fun selectProduct(produto: Produto) {
        selectedProduct.value = produto
    }
}
