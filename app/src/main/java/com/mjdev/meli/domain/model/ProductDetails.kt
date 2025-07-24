package com.mjdev.meli.domain.model

import java.math.BigDecimal


data class ProductDetails(
    val id: String,
    val title: String,
    val price: BigDecimal,
    val originalPrice: BigDecimal? = null,
    val currencyId: String,
    val imageUrl: String,
    val permalink: String,
    val warranty: String? = null,
    val condition: String? = null,
    val availableQuantity: Int? = null
)
