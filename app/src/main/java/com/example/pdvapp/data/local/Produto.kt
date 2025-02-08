package com.example.pdvapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val descricao: String,
    val codigoBarras: String,
    val preco: Double,
    val categoria: String,
    val quantidadeEstoque: Int
)