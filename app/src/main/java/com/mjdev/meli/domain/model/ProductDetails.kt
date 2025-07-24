package com.mjdev.meli.domain.model

import java.math.BigDecimal

/**
 * ProductDetails representa os detalhes de um produto do Mercado Livre.
 *
 * @property id ID único do produto.
 * @property title Título do produto.
 * @property price Preço atual do produto.
 * @property originalPrice Preço original do produto, se houver desconto.
 * @property currencyId ID da moeda
 * @property imageUrl URL da imagem do produto.
 * @property permalink URL do produto na plataforma.
 * @property warranty Garantia do produto, se disponível.
 * @property condition Condição do produto (novo, usado, etc.).
 * @property availableQuantity Quantidade disponível do produto em estoque.
 */
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
