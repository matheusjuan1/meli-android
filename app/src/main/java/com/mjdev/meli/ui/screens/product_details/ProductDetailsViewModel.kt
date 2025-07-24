package com.mjdev.meli.ui.screens.product_details

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
 * ViewModel para a tela de detalhes do produto.
 *
 * @property repository Repositório que fornece os dados do produto.
 */
class ProductDetailsViewModel(
    private val repository: IMeliRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    /**
     * Busca os detalhes de um produto dado seu ID.
     *
     * @param siteId O ID do site (MLA para a Argentina, MLB para o Brasil, etc...).
     * @param productId O ID único do produto a ser buscado.
     */
    fun getProductDetails(siteId: String, productId: String) {
        if (_uiState.value.isLoading) return

        _uiState.update {
            it.copy(
                isLoading = true,
                error = null,
                hasLoaded = true
            )
        }

        viewModelScope.launch {
            when (val result = repository.getProductDetails(siteId, productId)) {
                is DataResult.Success -> {
                    _uiState.update {
                        it.copy(
                            productDetails = result.value,
                            isLoading = false,
                            error = null
                        )
                    }
                }

                is DataResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            productDetails = null,
                            error = result.error
                        )
                    }
                }
            }
        }
    }
}