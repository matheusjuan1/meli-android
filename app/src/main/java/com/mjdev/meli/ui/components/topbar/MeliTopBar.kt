package com.mjdev.meli.ui.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mjdev.meli.R
import com.mjdev.meli.ui.theme.Main

/**
 * MeliTopBar é a barra de navegação superior personalizada.
 *
 * @param title O título a ser exibido na barra.
 * @param onBack A ação a ser executada quando o usuário clicar no ícone de voltar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeliTopBar(
    title: String = "Meli",
    onBack: () -> Unit
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Main
        ),
        title = {
            Text(title, fontWeight = FontWeight.SemiBold)
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                )
            }
        }
    )
}

@Preview
@Composable
private fun MeliTopBarPreview() {
    MeliTopBar(
        title = "Meli Top Bar",
        onBack = {}
    )
}