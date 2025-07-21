package com.mjdev.meli.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mjdev.meli.R
import com.mjdev.meli.ui.theme.Typography
import kotlinx.coroutines.delay

/**
 * SplashScreen é a tela inicial do aplicativo, exibida por 3 segundos
 * antes de navegar para a tela de pesquisa.
 *
 * @param onNavigateToSearch Callback de navegação para a tela de pesquisa.
 */
@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigateToSearch: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        delay(3_000)
        onNavigateToSearch()
    }

    Box(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .width(130.dp),
            painter = painterResource(id = R.drawable.img_meli_logo),
            contentDescription = stringResource(R.string.logo_meli)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 55.dp)
        ) {
            Text(
                text = stringResource(R.string.developed_by),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = Typography.bodySmall
            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(52.dp)
                    .width(52.dp),
                painter = painterResource(id = R.drawable.img_logo_mj),
                contentDescription = stringResource(R.string.logo_mj)
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(onNavigateToSearch = {})
}