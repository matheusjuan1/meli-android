package com.mjdev.meli.ui.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mjdev.meli.AppConfig
import com.mjdev.meli.R
import com.mjdev.meli.ui.components.button.MeliButton
import com.mjdev.meli.ui.components.topbar.MeliTopBar
import com.mjdev.meli.ui.screens.products.components.ProductCard
import com.mjdev.meli.ui.theme.Gray200
import com.mjdev.meli.ui.theme.Gray400
import com.mjdev.meli.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

/**
 * ProductsScreen é a tela que exibe os produtos encontrados com base na pesquisa.
 *
 * @param query A consulta de pesquisa para filtrar os produtos.
 * @param onBackClick Ação a ser executada quando o usuário clicar no botão de voltar.
 * @param onProductClick Ação a ser executada quando o usuário clicar em um produto.
 * @param viewModel ViewModel para gerenciar o estado da tela.
 */
@Composable
fun ProductsScreen(
    query: String,
    onBackClick: () -> Unit,
    onProductClick: (productId: String) -> Unit,
    viewModel: ProductsViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(query) {
        if (query.isNotBlank()) {
            viewModel.searchProducts(AppConfig.SITE_ID, query)
        }
    }

    Scaffold(
        topBar = {
            MeliTopBar(
                title = stringResource(R.string.search),
                onBack = {
                    onBackClick()
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                uiState.error != null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.7f)
                    ) {

                        Image(
                            modifier = Modifier
                                .width(150.dp)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(R.drawable.img_error),
                            contentDescription = stringResource(R.string.error_image)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = Typography.headlineSmall,
                            text = uiState.error?.message ?: stringResource(R.string.error_generic)
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        MeliButton(text = stringResource(R.string.back), onClick = {
                            onBackClick()
                        })
                    }
                }

                uiState.products.isEmpty() && uiState.hasSearched && !uiState.isLoading -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.7f),
                        verticalArrangement = Arrangement.spacedBy(48.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.no_results, query)
                        )

                        MeliButton(text = stringResource(R.string.new_search), onClick = {
                            onBackClick()
                        })
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 4.dp),
                    ) {
                        item {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.result_for, query),
                                    style = Typography.titleLarge,
                                )
                                Spacer(Modifier.height(4.dp))

                                Text(
                                    text = if (uiState.products.size == 1) {
                                        stringResource(R.string.one_products)
                                    } else {
                                        stringResource(R.string.n_products, uiState.products.size)
                                    },
                                    style = Typography.bodySmall,
                                    color = Gray400,
                                )
                            }
                        }

                        itemsIndexed(uiState.products) { index, product ->
                            ProductCard(
                                product = product,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                onClick = {
                                    onProductClick(product.id)
                                }
                            )
                            if (index < uiState.products.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp),
                                    color = Gray200
                                )
                            } else {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}