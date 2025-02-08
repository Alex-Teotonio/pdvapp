package com.example.pdvapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.pdvapp.data.local.AppDatabase
import com.example.pdvapp.data.local.Produto
import com.example.pdvapp.data.local.ProdutoDao

class ProdutoRepository(application: Application) {
    private val produtoDao: ProdutoDao =
        AppDatabase.getDatabase(application).produtoDao()

    // Expondo o LiveData com a lista de produtos
    val produtos: LiveData<List<Produto>> = produtoDao.listarProdutos()

    // Função para inserir um novo produto
    suspend fun inserir(produto: Produto) {
        produtoDao.inserir(produto)
    }

    // Função para atualizar um produto existente
    suspend fun atualizar(produto: Produto) {
        produtoDao.atualizar(produto)
    }

    // Função para excluir um produto
    suspend fun excluir(produto: Produto) {
        produtoDao.excluir(produto)
    }

    // Função para buscar produtos com base em uma query
    fun buscar(query: String): LiveData<List<Produto>> {
        return produtoDao.buscarProdutos(query)
    }
}
