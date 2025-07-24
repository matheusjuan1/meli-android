package com.mjdev.meli.data.repository.mock

import android.content.Context
import android.util.Log
import com.mjdev.meli.data.remote.model.ProductDetailsResponse
import com.mjdev.meli.data.remote.model.SearchResponse
import com.mjdev.meli.data.remote.util.AppJson
import com.mjdev.meli.data.remote.util.toDomainProduct
import com.mjdev.meli.data.remote.util.toDomainProductDetails
import com.mjdev.meli.domain.exception.MeliException
import com.mjdev.meli.domain.model.Product
import com.mjdev.meli.domain.model.ProductDetails
import com.mjdev.meli.domain.repository.IMeliRepository
import com.mjdev.meli.domain.util.DataResult
import java.io.IOException

/**
 * MeliRepositoryMock é uma implementação que simula a interação com a API do Mercado Livre,
 * utilizando arquivos JSON mockados armazenados na pasta assets do aplicativo.
 *
 * @param context Contexto da aplicação, usado para acessar os recursos de assets.
 */
class MeliRepositoryMock(private val context: Context) : IMeliRepository {

    private val TAG = "MeliRepositoryMock"

    override suspend fun searchProducts(siteId: String, query: String): DataResult<List<Product>> {
        val fileName = "search-${siteId.uppercase()}-${query.lowercase().trim()}.json"

        return try {
            val jsonString = loadJsonFromAssets("search", fileName)
            if (jsonString == null) {
                Log.w(TAG, "Arquivo mock não encontrado para: $fileName. Retornando lista vazia.")
                DataResult.Success(emptyList())
            } else {
                val searchResponse = AppJson.decodeFromString<SearchResponse>(jsonString)
                val products = searchResponse.results?.map { it.toDomainProduct() } ?: emptyList()
                Log.d(
                    TAG,
                    "Mock da busca '$query' carregado. Encontrados ${products.size} produtos."
                )
                DataResult.Success(products)
            }
        } catch (e: Exception) {
            Log.e(
                TAG,
                "Erro ao carregar ou parsear JSON mock para busca '$query': ${e.localizedMessage}",
                e
            )
            DataResult.Error(
                MeliException.UnknownException(
                    "Erro ao carregar dados mockados: ${e.localizedMessage}",
                    e
                )
            )
        }
    }

    override suspend fun getProductDetails(
        siteId: String,
        productId: String
    ): DataResult<ProductDetails> {
        val fileName = "item-$productId.json"

        return try {
            val jsonString = loadJsonFromAssets("products", fileName)
            if (jsonString == null) {
                Log.e(TAG, "Arquivo mock de detalhes não encontrado para ID: $productId.")
                DataResult.Error(MeliException.UnknownException("Detalhes do produto não encontrados."))
            } else {
                val apiProductDetails =
                    AppJson.decodeFromString<ProductDetailsResponse>(jsonString)
                val productDetails = apiProductDetails.toDomainProductDetails()
                Log.d(TAG, "Mock de detalhes para '$productId' carregado com sucesso.")
                DataResult.Success(productDetails)
            }
        } catch (e: Exception) {
            Log.e(
                TAG,
                "Erro ao carregar ou parsear JSON mock de detalhes para '$productId': ${e.localizedMessage}",
                e
            )
            DataResult.Error(
                MeliException.UnknownException(
                    "Erro ao carregar dados mockados dos detalhes: ${e.localizedMessage}",
                    e
                )
            )
        }
    }

    /**
     * Carrega um arquivo JSON dos produtos mockados a partir da pasta assets.
     */
    private fun loadJsonFromAssets(folder: String, fileName: String): String? {
        return try {
            val inputStream = context.assets.open("mock/$folder/$fileName")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            Log.e(
                TAG,
                "Erro ao ler arquivo json 'mock/products/$folder/$fileName': ${e.localizedMessage}",
                e
            )
            null
        }
    }
}