package com.example.pdvapp.data.ticket

data class Evento(
    val id: Long,
    val nome: String,
    val data: String,  // Pode ser uma String ou um tipo Date/LocalDate, conforme sua necessidade
    val local: String,
    val descricao: String
)
