package com.mjdev.meli.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("site_id") val siteId: String? = null,
    @SerialName("query") val query: String? = null,
    @SerialName("paging") val paging: Paging? = null,
    @SerialName("results") val results: List<ProductResult>? = null,
)

@Serializable
data class Paging(
    @SerialName("total") val total: Int? = null,
    @SerialName("primary_results") val primaryResults: Int? = null,
    @SerialName("offset") val offset: Int? = null,
    @SerialName("limit") val limit: Int? = null
)


@Serializable
data class ProductResult(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("price") val price: Double? = null,
    @SerialName("original_price") val originalPrice: Double? = null,
    @SerialName("permalink") val permalink: String? = null,
    @SerialName("currency_id") val currencyId: String? = null,
    @SerialName("official_store_name") val officialStoreName: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null,
    @SerialName("shipping") val shipping: Shipping? = null,
    @SerialName("attributes") val attributes: List<Attribute>? = null
)

@Serializable
data class Shipping(
    @SerialName("mode") val mode: String? = null,
    @SerialName("tags") val tags: List<@Serializable String>? = null
)

@Serializable
data class Attribute(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("value_name") val valueName: String? = null
)
