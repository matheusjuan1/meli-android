package com.mjdev.meli.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsResponse(
    val id: String,
    @SerialName("site_id") val siteId: String,
    val title: String,
    @SerialName("seller_id") val sellerId: Long,
    @SerialName("category_id") val categoryId: String,
    @SerialName("official_store_id") val officialStoreId: Long? = null,
    val price: Double,
    @SerialName("base_price") val basePrice: Double,
    @SerialName("original_price") val originalPrice: Double? = null,
    @SerialName("currency_id") val currencyId: String,
    @SerialName("initial_quantity") val initialQuantity: Int,
    @SerialName("buying_mode") val buyingMode: String,
    @SerialName("listing_type_id") val listingTypeId: String,
    val condition: String,
    val permalink: String,
    @SerialName("thumbnail_id") val thumbnailId: String,
    val thumbnail: String,
    val pictures: List<ProductPicture>,
    val warranty: String? = null,
)

@Serializable
data class ProductPicture(
    val id: String,
    val url: String,
    @SerialName("secure_url") val secureUrl: String,
    val size: String,
    @SerialName("max_size") val maxSize: String,
    val quality: String
)
