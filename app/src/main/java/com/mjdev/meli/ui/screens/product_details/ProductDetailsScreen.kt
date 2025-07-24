package com.mjdev.meli.ui.screens.product_details

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mjdev.meli.AppConfig
import com.mjdev.meli.R
import com.mjdev.meli.ui.components.button.MeliButton
import com.mjdev.meli.ui.components.topbar.MeliTopBar
import com.mjdev.meli.ui.theme.Gray400
import com.mjdev.meli.ui.theme.Gray500
import com.mjdev.meli.ui.theme.Gray600
import com.mjdev.meli.ui.theme.Typography
import com.mjdev.meli.ui.util.currencyFormat
import org.koin.androidx.compose.koinViewModel
import java.util.Currency

/**
 * ProductDetailsScreen é a tela que exibe os detalhes de um produto específico.
 *
 * @param productId O ID do produto a ser exibido.
 * @param onBackClick A ação a ser executada quando o usuário clicar no botão de voltar.
 * @param viewModel O ViewModel responsável por gerenciar o estado da tela.
 */
@Composable
fun ProductDetailsScreen(
    productId: String,
    onBackClick: () -> Unit,
    viewModel: ProductDetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    LaunchedEffect(productId) {
        if (productId.isNotBlank()) {
            viewModel.getProductDetails(AppConfig.SITE_ID, productId)
        }
    }

    Scaffold(
        topBar = {
            MeliTopBar(
                title = stringResource(R.string.product_details),
                onBack = onBackClick
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
                                .width(120.dp)
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

                uiState.productDetails != null -> {
                    val product = uiState.productDetails!!

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(scrollState)
                    ) {
                        Text(
                            text = product.title,
                            style = Typography.bodyLarge,
                            color = Gray600,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(8.dp))

                        AsyncImage(
                            model = product.imageUrl,
                            contentDescription = product.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(bottom = 16.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(Modifier.height(16.dp))

                        if (product.originalPrice != null && product.originalPrice > product.price) {
                            Text(
                                text = product.originalPrice.currencyFormat(
                                    currency = Currency.getInstance(product.currencyId)
                                ),
                                style = Typography.bodyLarge.copy(
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Gray400
                                ),
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Text(
                            text = product.price.currencyFormat(
                                currency = Currency.getInstance(product.currencyId)
                            ),
                            style = Typography.headlineLarge,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(24.dp))

                        if (!product.condition.isNullOrBlank()) {
                            Text(
                                text = stringResource(
                                    R.string.condition_x,
                                    product.condition.replaceFirstChar { it.uppercase() }),
                                style = Typography.bodySmall,
                                color = Gray500
                            )
                        }
                        if (!product.warranty.isNullOrBlank()) {
                            Text(
                                text = product.warranty,
                                style = Typography.bodySmall,
                                color = Gray500
                            )
                        }

                        if (product.availableQuantity != null && product.availableQuantity > 0) {
                            Text(
                                text = stringResource(
                                    R.string.n_availables,
                                    product.availableQuantity
                                ),
                                style = Typography.bodySmall,
                                color = Gray500
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.out_of_stock),
                                style = Typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                color = Gray500
                            )
                        }
                        Spacer(modifier = Modifier.height(48.dp))

                        MeliButton(text = stringResource(R.string.go_to_meli), onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, product.permalink.toUri())
                            context.startActivity(intent)
                        })
                    }
                }
            }
        }
    }
}