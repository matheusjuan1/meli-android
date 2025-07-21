package com.mjdev.meli.ui.routes

import kotlinx.serialization.Serializable

@Serializable
data object SplashRoute

@Serializable
data object SearchRoute

@Serializable
data class ResultRoute(val searchQuery: String)

@Serializable
data class ProductDetailRoute(val productId: String)