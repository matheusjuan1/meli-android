package com.mjdev.meli.data.repository

import com.mjdev.meli.base.BaseRepositoryTest
import com.mjdev.meli.data.remote.model.ProductDto
import com.mjdev.meli.data.remote.model.SearchResponseDto
import com.mjdev.meli.domain.exception.MeliException
import com.mjdev.meli.domain.util.DataResult
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class MeliRepositoryTest : BaseRepositoryTest() {

    private lateinit var repository: MeliRepository

    override fun setup() {
        super.setup()
        repository = MeliRepository(mockApiService)
    }


    @Test
    fun `searchProducts should return Success with mapped products on API success`() = runTest {
        val apiProducts = listOf(
            ProductDto(
                id = "1",
                title = "Product A API",
                thumbnail = "thumbA",
                permalink = "linkA",
                price = 100.00,
                originalPrice = 150.00,
                currencyId = "BRL",
                officialStoreName = "Loja Oficial A",
                shipping = null,
                attributes = emptyList(),
            )
        )
        val searchResponse = SearchResponseDto(
            siteId = "MLA",
            query = "test",
            results = apiProducts,
            paging = null,
        )
        coEvery { mockApiService.searchProducts(any(), any()) } returns searchResponse

        val result = repository.searchProducts("MLA", "test")

        assertTrue(result is DataResult.Success)
        val successData = (result as DataResult.Success).data
        assertEquals(1, successData.size)
        assertEquals("Product A API", successData[0].title)
        assertEquals(0, successData[0].price.compareTo(BigDecimal("100.00")))
    }

    @Test
    fun `searchProducts should return Success with empty list if API returns null results`() =
        runTest {
            val searchResponse = SearchResponseDto(
                siteId = "MLA",
                query = "test",
                results = null,
                paging = null,
            )
            coEvery { mockApiService.searchProducts(any(), any()) } returns searchResponse

            val result = repository.searchProducts("MLA", "test")

            assertTrue(result is DataResult.Success)
            val successData = (result as DataResult.Success).data
            assertTrue(successData.isEmpty())
        }


    @Test
    fun `searchProducts should return Error on API failure (HttpException)`() = runTest {
        val errorBody =
            "{\"message\":\"Unauthorized\",\"code\":\"unauthorized\"}".toResponseBody(null)
        val httpException = HttpException(retrofit2.Response.error<Any>(401, errorBody))
        coEvery { mockApiService.searchProducts(any(), any()) } throws httpException

        val result = repository.searchProducts("MLA", "test")

        assertTrue(result is DataResult.Error)
        val errorException = (result as DataResult.Error).error
        assertTrue(errorException is MeliException.ApiException)
        assertEquals("Unauthorized", errorException.message)
        assertEquals(401, (errorException as MeliException.ApiException).statusCode)
    }

    @Test
    fun `searchProducts should return Error on network failure (IOException)`() = runTest {
        val ioException = java.io.IOException("Network unreachable")
        coEvery { mockApiService.searchProducts(any(), any()) } throws ioException

        val result = repository.searchProducts("MLA", "test")


        assertTrue(result is DataResult.Error)
        val errorException = (result as DataResult.Error).error

        assertTrue(errorException is MeliException.ApiException.NetworkError)
        assertEquals("Network unreachable", errorException.message)
    }
}