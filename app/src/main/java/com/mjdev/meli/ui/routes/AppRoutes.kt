package com.mjdev.meli.ui.routes

import kotlinx.serialization.Serializable

/**
 * AppRoutes define as rotas do aplicativo, que são usadas para navegar entre diferentes telas.
 * Cada rota é representada por um objeto serializável.
 */

/**
 * SplashScreen
 */
@Serializable
data object SplashRoute

/**
 * SearchScreen
 */
@Serializable
data object SearchRoute

/**
 * ProductsScreen
 *
 * @param searchQuery Texto de consulta usado para buscar produtos.
 */
@Serializable
data class ProductsRoute(val searchQuery: String)

/**
 * ProductDetailScreen
 *
 * @param productId ID do produto para exibir detalhes.
 */
@Serializable
data class ProductDetailRoute(val productId: String)