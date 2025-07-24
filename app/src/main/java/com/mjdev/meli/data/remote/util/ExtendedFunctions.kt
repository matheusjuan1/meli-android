package com.mjdev.meli.data.remote.util

import com.mjdev.meli.data.remote.model.ProductDetailsDto
import com.mjdev.meli.data.remote.model.ProductDto
import com.mjdev.meli.domain.model.Product
import com.mjdev.meli.domain.model.ProductDetails
import java.math.BigDecimal

/**
 * Função para converter um objeto [ProductDto] em um objeto [Product].
 */
fun ProductDto.toDomainProduct(): Product {
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

/**
 * Função para converter um objeto [ProductDetailsDto] em um objeto [ProductDetails].
 */
fun ProductDetailsDto.toDomainProductDetails(): ProductDetails {
    return ProductDetails(
        id = this.id,
        title = this.title,
        price = BigDecimal(this.price),
        originalPrice = this.originalPrice?.let { BigDecimal(it) },
        currencyId = this.currencyId,
        availableQuantity = this.initialQuantity,
        condition = this.condition,
        permalink = this.permalink,
        warranty = this.warranty,
        imageUrl = this.thumbnail
    )
}