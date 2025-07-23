package com.mjdev.meli.ui.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mjdev.meli.R
import com.mjdev.meli.ui.screens.products.components.ProductCard
import com.mjdev.meli.ui.theme.Gray400
import com.mjdev.meli.ui.theme.RedBase
import com.mjdev.meli.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsScreen(
    query: String,
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onProductClick: (productId: String) -> Unit,
    viewModel: ProductsViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(query) {
        if (query.isNotBlank()) {
            viewModel.searchProducts("MLA", query)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column {
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.result_for, query),
                style = Typography.titleLarge,
            )
            Spacer(Modifier.height(4.dp))

            Text(
                text = "${uiState.products.size} produtos",
                style = Typography.bodySmall,
                color = Gray400,
            )
            Spacer(Modifier.height(8.dp))

            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                Text("Buscando produtos...")
            }

            when {
                uiState.error != null -> {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Erro: ${uiState.error?.message}",
                        color = RedBase,
                    )
                }

                uiState.products.isEmpty() && uiState.hasSearched && !uiState.isLoading -> {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Nenhum resultado encontrado para \"$query\".",
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.products) { product ->
                            ProductCard(
                                product = product,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}