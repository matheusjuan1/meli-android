package com.mjdev.meli.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mjdev.meli.R
import com.mjdev.meli.ui.components.button.MeliButton
import com.mjdev.meli.ui.components.textfield.MeliTextField
import com.mjdev.meli.ui.theme.Typography

/**
 * SearchScreen é a tela de pesquisa do aplicativo, onde o usuário pode digitar
 * uma consulta e iniciar a busca.
 *
 * @param paddingValues Valores de preenchimento para a tela, usado para evitar sobreposição com barras de status ou navegação.
 * @param onSearch Callback que é chamado quando o usuário clica no botão de buscar.
 */
@Composable
fun SearchScreen(paddingValues: PaddingValues, onSearch: (String) -> Unit) {

    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.weight(0.2f))
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.img_meli_logo),
                contentDescription = stringResource(id = R.string.logo_meli)
            )

            Spacer(modifier = Modifier.weight(0.2f))

            Text(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                text = stringResource(R.string.search_hint),
                style = Typography.headlineSmall.copy(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.weight(0.15f))

            MeliTextField(
                modifier = Modifier.fillMaxWidth(0.9f),
                value = searchQuery,
                placeHolder = stringResource(id = R.string.type_here),
                onValueChange = { searchQuery = it }
            )

            Spacer(modifier = Modifier.weight(0.4f))

            MeliButton(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = stringResource(id = R.string.search),
                iconRes = R.drawable.ic_search,
                enabled = searchQuery.isNotBlank(),
                onClick = {
                    onSearch(searchQuery)
                }
            )

            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(paddingValues = PaddingValues(), onSearch = { })
}