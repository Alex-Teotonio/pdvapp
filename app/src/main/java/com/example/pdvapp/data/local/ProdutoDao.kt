package com.example.pdvapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProdutoDao {
    @Insert
    suspend fun inserir(produto: Produto)

    @Update
    suspend fun atualizar(produto: Produto)

    @Delete
    suspend fun excluir(produto: Produto)

    @Query("SELECT * FROM produtos ORDER BY nome")
    fun listarProdutos(): LiveData<List<Produto>>

    @Query("SELECT * FROM produtos WHERE nome LIKE :query OR codigoBarras LIKE :query")
    fun buscarProdutos(query: String): LiveData<List<Produto>>
}
