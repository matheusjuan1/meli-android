package com.mjdev.meli.ui.screens.product_details

import com.mjdev.meli.domain.exception.MeliException
import com.mjdev.meli.domain.model.ProductDetails

/**
 * ProductDetailsUiState representa o estado da UI para a tela de detalhes do produto.
 *
 * @property productDetails Detalhes do produto carregados, ou null se não houver.
 * @property isLoading Indica se os detalhes do produto estão sendo carregados.
 * @property error Possível erro ocorrido durante o carregamento dos detalhes do produto.
 * @property hasLoaded Indica se os detalhes do produto já foram carregados.
 */
data class ProductDetailsUiState(
    val productDetails: ProductDetails? = null,
    val isLoading: Boolean = false,
    val error: MeliException? = null,
    val hasLoaded: Boolean = false
)