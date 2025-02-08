package com.example.pdvapp.data.local

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CartItem(
    val produto: Produto,
    val quantidade: MutableState<Int> = mutableStateOf(1)
)
