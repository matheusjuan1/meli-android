package com.mjdev.meli.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjdev.meli.domain.repository.IMeliRepository
import com.mjdev.meli.domain.util.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel para a tela de produtos.
 *
 * @property repository Repositório que fornece os dados dos produtos.
 */
class ProductsViewModel(
    private val repository: IMeliRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsScreenState())
    val uiState: StateFlow<ProductsScreenState> = _uiState.asStateFlow()

    /**
     * Busca produtos com base na consulta fornecida.
     *
     * @param siteId ID do site onde os produtos serão buscados (MLA para a Argentina, MLB para o Brasil, etc...).
     * @param query Texto de consulta para buscar produtos.
     */
    fun searchProducts(siteId: String, query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, hasSearched = true) }


            when (val result = repository.searchProducts(siteId, query)) {
                is DataResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = result.value,
                            error = null
                        )
                    }
                }

                is DataResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = emptyList(),
                            error = result.error
                        )
                    }
                }
            }
        }
    }
}