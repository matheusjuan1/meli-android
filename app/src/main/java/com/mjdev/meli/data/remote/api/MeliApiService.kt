package com.mjdev.meli.data.remote.api

import com.mjdev.meli.data.remote.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * MeliApiService é a interface que define os endpoints da API do Mercado Livre.
 */
interface MeliApiService {

    /**
     * Busca produtos com base na consulta fornecida.
     *
     * @param siteId ID do site onde os produtos serão buscados (MLA para a Argentina, MLB para o Brasil, etc...).
     * @param query Texto de consulta para buscar produtos.
     * @return SearchResponse contendo os resultados da busca.
     */
    @GET("sites/{site_id}/search")
    suspend fun searchProducts(
        @Path("site_id") siteId: String,
        @Query("q") query: String
    ): SearchResponse
}