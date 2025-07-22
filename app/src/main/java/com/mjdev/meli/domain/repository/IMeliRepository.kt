package com.mjdev.meli.domain.repository

import com.mjdev.meli.domain.model.Product
import com.mjdev.meli.domain.util.DataResult

/**
 * IMeliRepository é a interface que define os métodos a serem implementados pela fonte de dados
 *
 */
interface IMeliRepository {

    /**
     * Busca produtos com base na consulta fornecida.
     *
     * @param siteId ID do site onde os produtos serão buscados (MLA para a Argentina, MLB para o Brasil, etc...).
     * @param query Texto de consulta para buscar produtos.
     * @return Lista de produtos correspondentes à consulta.
     */
    suspend fun searchProducts(siteId: String, query: String): DataResult<List<Product>>
}