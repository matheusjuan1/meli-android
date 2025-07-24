package com.mjdev.meli.ui.screens.products

import com.mjdev.meli.domain.exception.MeliException
import com.mjdev.meli.domain.model.Product

/**
 * ProductsUiState representa o estado da Ui para a tela de produtos.
 *
 * @property isLoading Indica se os produtos estão sendo carregados.
 * @property products Lista de produtos carregados.
 * @property error Possível erro ocorrido durante o carregamento dos produtos.
 * @property hasSearched Indica se uma busca foi realizada.
 */
data class ProductsUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: MeliException? = null,
    val hasSearched: Boolean = false
)