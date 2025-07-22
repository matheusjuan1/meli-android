package com.mjdev.meli.domain.model

import java.math.BigDecimal

/**
 * Product representa um produto do Mercado Livre.
 *
 * @property id ID único do produto.
 * @property title Título do produto.
 * @property price Preço do produto.
 * @property currencyId ID da moeda do preço do produto.
 * @property imageUrl URL da imagem do produto.
 * @property isFreeShipping Indica se o produto tem frete grátis.
 */
data class Product(
    val id: String,
    val title: String,
    val price: BigDecimal,
    val currencyId: String,
    val imageUrl: String,
    val isFreeShipping: Boolean
)
