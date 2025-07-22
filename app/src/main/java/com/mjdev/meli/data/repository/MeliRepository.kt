package com.mjdev.meli.data.repository

import com.mjdev.meli.data.remote.api.MeliApiService
import com.mjdev.meli.data.remote.util.toDomainProduct
import com.mjdev.meli.domain.exception.MeliException
import com.mjdev.meli.domain.model.Product
import com.mjdev.meli.domain.repository.IMeliRepository
import com.mjdev.meli.domain.util.DataResult
import com.mjdev.meli.domain.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * MeliRepository é a implementação da interface IMeliRepository,
 * responsável por interagir com a API do Mercado Livre para buscar produtos.
 *
 * @param apiService Instância do serviço de API do Mercado Livre.
 */
class MeliRepository(private val apiService: MeliApiService) : IMeliRepository {

    override suspend fun searchProducts(siteId: String, query: String): DataResult<List<Product>> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                val apiResponse = apiService.searchProducts(siteId, query)
                apiResponse.results?.map { it.toDomainProduct() } ?: emptyList()
            }
        }
    }

    override suspend fun getProductDetails(siteId: String, productId: String): DataResult<Product> {
        return DataResult.Error(MeliException.UnknownException("Método não implementado"))
    }
}