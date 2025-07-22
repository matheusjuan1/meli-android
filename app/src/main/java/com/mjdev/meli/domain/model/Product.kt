package com.mjdev.meli.domain.model

import java.math.BigDecimal

/**
 * Product representa um produto do Mercado Livre.
 *
 * @property id ID único do produto.
 * @property title Título do produto.
 * @property price Preço do produto.
 * @property originalPrice Preço original do produto, se disponível.
 * @property permalink URL do produto no Mercado Livre.
 * @property currencyId ID da moeda do preço do produto.
 * @property officialStoreName Nome da loja oficial do produto, se aplicável.
 * @property imageUrl URL da imagem do produto.
 */
data class Product(
    val id: String,
    val title: String,
    val price: BigDecimal,
    val originalPrice: BigDecimal? = null,
    val permalink: String,
    val currencyId: String,
    val officialStoreName: String,
    val imageUrl: String,
)
