package com.mjdev.meli.data.repository.mock

import android.content.Context
import android.util.Log
import com.mjdev.meli.data.remote.model.SearchResponse
import com.mjdev.meli.data.remote.util.toDomainProduct
import com.mjdev.meli.domain.exception.MeliException
import com.mjdev.meli.domain.model.Product
import com.mjdev.meli.domain.model.ProductDetails
import com.mjdev.meli.domain.repository.IMeliRepository
import com.mjdev.meli.domain.util.DataResult
import kotlinx.serialization.json.Json
import java.io.IOException

/**
 * MeliRepositoryMock é uma implementação que simula a interação com a API do Mercado Livre,
 * utilizando arquivos JSON mockados armazenados na pasta assets do aplicativo.
 *
 * @param context Contexto da aplicação, usado para acessar os recursos de assets.
 */
class MeliRepositoryMock(private val context: Context) : IMeliRepository {

    private val TAG = "MeliRepositoryMock"

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    override suspend fun searchProducts(siteId: String, query: String): DataResult<List<Product>> {
        val folderName = query.lowercase().trim()
        val fileName = "search-${siteId.uppercase()}-${folderName}.json"

        return try {
            val jsonString = loadJsonFromAssets(folderName, fileName)
            if (jsonString == null) {
                Log.w(TAG, "Arquivo mock não encontrado para: $fileName. Retornando lista vazia.")
                DataResult.Success(emptyList())
            } else {
                val searchResponse = jsonParser.decodeFromString<SearchResponse>(jsonString)
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
        return DataResult.Error(MeliException.UnknownException("Método não implementado"))
    }

    /**
     * Carrega um arquivo JSON dos produtos mockados a partir da pasta assets.
     */
    private fun loadJsonFromAssets(folder: String, fileName: String): String? {
        return try {
            val inputStream = context.assets.open("mock/products/$folder/$fileName")
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