package com.mjdev.meli.data.remote.util

import com.mjdev.meli.data.remote.model.ProductResult
import com.mjdev.meli.domain.model.Product
import java.math.BigDecimal

/**
 * Função para converter um objeto [ProductResult] em um objeto [Product].
 */
fun ProductResult.toDomainProduct(): Product {
    return Product(
        id = this.id ?: "",
        title = this.title ?: "",
        price = BigDecimal(this.price ?: 0.0),
        originalPrice = BigDecimal(this.originalPrice ?: 0.0),
        permalink = this.permalink ?: "",
        currencyId = this.currencyId ?: "BRL",
        officialStoreName = this.officialStoreName ?: "",
        imageUrl = this.thumbnail ?: "",
    )
}