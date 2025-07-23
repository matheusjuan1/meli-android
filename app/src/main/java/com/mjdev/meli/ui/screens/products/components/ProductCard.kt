package com.mjdev.meli.ui.screens.products.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mjdev.meli.domain.model.Product
import com.mjdev.meli.ui.theme.Gray600
import com.mjdev.meli.ui.theme.Typography
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    style = Typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                val priceFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                priceFormatter.currency = Currency.getInstance(product.currencyId)
                Text(
                    text = priceFormatter.format(product.price),
                    style = Typography.bodyMedium,
                    color = Gray600
                )
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