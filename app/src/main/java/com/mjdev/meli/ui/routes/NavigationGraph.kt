package com.mjdev.meli.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mjdev.meli.ui.screens.products.ProductsScreen
import com.mjdev.meli.ui.screens.search.SearchScreen
import com.mjdev.meli.ui.screens.splash.SplashScreen

/**
 * AppNavigation é o componente responsável por gerenciar a navegação
 * dentro do aplicativo, definindo as rotas e as telas correspondentes.
 */
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(
                onNavigateToSearch = {
                    navController.navigate(SearchRoute) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<SearchRoute> {
            SearchScreen(
                onSearch = { query ->
                    navController.navigate(ProductsRoute(query))
                }
            )
        }

        composable<ProductsRoute> { backStackEntry ->
            val searchQuery = backStackEntry.arguments?.getString("searchQuery") ?: ""
            ProductsScreen(
                query = searchQuery,
                onBackClick = {
                    navController.popBackStack()
                },
                onProductClick = { }
            )
        }

        composable<ProductDetailRoute> { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            // TODO
        }
    }
}