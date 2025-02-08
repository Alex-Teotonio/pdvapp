package com.example.pdvapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pdvapp.data.local.Produto
import com.example.pdvapp.repository.ProdutoRepository
import kotlinx.coroutines.launch

class ProdutoViewModel(application: Application) : AndroidViewModel(application) {

    // Instância do repositório
    private val repository: ProdutoRepository

    // LiveData com a lista de produtos
    val produtos: LiveData<List<Produto>>

    init {
        // Instancia o repositório passando o Application
        repository = ProdutoRepository(application)
        produtos = repository.produtos
    }

    // Função para inserir um novo produto
    fun inserir(produto: Produto) = viewModelScope.launch {
        repository.inserir(produto)
    }

    // Função para atualizar um produto existente
    fun atualizar(produto: Produto) = viewModelScope.launch {
        repository.atualizar(produto)
    }

    // Função para excluir um produto
    fun excluir(produto: Produto) = viewModelScope.launch {
        repository.excluir(produto)
    }

    // Função para buscar produtos com base em uma query
    fun buscar(query: String): LiveData<List<Produto>> {
        return repository.buscar(query)
    }
}
