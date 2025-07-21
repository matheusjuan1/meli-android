package com.mjdev.meli.ui.routes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mjdev.meli.ui.screens.splash.SplashScreen

/**
 * AppNavigation é o componente responsável por gerenciar a navegação
 * dentro do aplicativo, definindo as rotas e as telas correspondentes.
 *
 * @param navController Controlador de navegação usado para gerenciar as rotas.
 */
@Composable
fun AppNavigation(
    paddingValues: PaddingValues,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(
                paddingValues = paddingValues,
                onNavigateToSearch = {
                    navController.navigate(SearchRoute) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<SearchRoute> {
            // TODO
        }

        composable<ResultRoute> { backStackEntry ->
            val searchQuery = backStackEntry.arguments?.getString("searchQuery") ?: ""
            // TODO
        }

        composable<ProductDetailRoute> { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            // TODO
        }
    }
}