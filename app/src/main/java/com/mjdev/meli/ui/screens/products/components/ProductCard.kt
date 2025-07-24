package com.mjdev.meli.ui.screens.products.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mjdev.meli.R
import com.mjdev.meli.domain.model.Product
import com.mjdev.meli.ui.theme.Gray100
import com.mjdev.meli.ui.theme.Gray400
import com.mjdev.meli.ui.theme.Gray500
import com.mjdev.meli.ui.theme.Gray600
import com.mjdev.meli.ui.theme.Typography
import com.mjdev.meli.ui.util.currencyFormat
import java.math.BigDecimal
import java.util.Currency

/**
 * ProductCard é o componente que exibe as informações de um produto em um card.
 *
 * @param product O produto a ser exibido.
 * @param modifier Modificador opcional para personalizar o layout do card.
 */
@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray100
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth(0.3f)
                    .height(IntrinsicSize.Min)
                    .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true),
                model = product.imageUrl,
                contentDescription = product.title,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    style = Typography.bodyLarge,
                    maxLines = 2,
                    color = Gray500,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                val originalPriceHeight = 15.dp
                if (product.originalPrice != null && product.originalPrice > product.price) {
                    Text(
                        text = product.originalPrice.currencyFormat(
                            currency = Currency.getInstance(product.currencyId),
                        ),
                        style = Typography.bodySmall.copy(
                            textDecoration = TextDecoration.LineThrough,
                            color = Gray400
                        ),
                        modifier = Modifier.height(originalPriceHeight)
                    )
                } else {
                    Spacer(modifier = Modifier.height(originalPriceHeight))
                }

                Text(
                    text = product.price.currencyFormat(
                        currency = Currency.getInstance(product.currencyId),
                    ),
                    style = Typography.labelMedium.copy(fontSize = 18.sp),
                    color = Gray600
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (product.officialStoreName.isNotBlank()) {
                    Text(
                        text = stringResource(
                            R.string.sold_by, product.officialStoreName
                        ),
                        style = Typography.bodySmall,
                        color = Gray400,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProductCardPreview() {
    ProductCard(
        product = Product(
            id = "MLB123456789",
            title = "Produto de Exemplo",
            price = BigDecimal(123.5),
            originalPrice = BigDecimal(150.0),
            permalink = "https://www.mercadolivre.com.br/produto-exemplo",
            officialStoreName = "Loja Oficial Exemplo",
            currencyId = "BRL",
            imageUrl = "http://http2.mlstatic.com/D_619667-MLA47781882790_102021-I.jpg"
        ),
        modifier = Modifier.padding(16.dp)
    )
}