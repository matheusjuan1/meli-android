package com.mjdev.meli.ui.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mjdev.meli.R
import com.mjdev.meli.ui.theme.Gray300
import com.mjdev.meli.ui.theme.Gray400
import com.mjdev.meli.ui.theme.Gray600
import com.mjdev.meli.ui.theme.Main
import com.mjdev.meli.ui.theme.Typography


/**
 * MeliButton é um componente de botão reutilizável com suporte a ícones e texto.
 *
 * @param modifier Modificador para personalizar o layout do botão.
 * @param text Texto a ser exibido no botão. Se `null`, apenas o ícone será exibido.
 * @param iconRes Recurso drawable do ícone a ser exibido no botão. Se `null`, apenas o texto será exibido.
 * @param color Cor de fundo do botão. Padrão é a `Main` color.
 * @param textColor Cor do texto do botão. Padrão é `Gray600`.
 * @param enabled Indica se o botão está habilitado. Padrão é `true`.
 * @param onClick Ação a ser executada quando o botão for clicado.
 */
@Composable
fun MeliButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes iconRes: Int? = null,
    color: Color = Main,
    textColor: Color = Gray600,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.heightIn(min = 42.dp),
        shape = RoundedCornerShape(16.dp),
        enabled = enabled,
        contentPadding =
            if (text == null && iconRes != null) PaddingValues(0.dp)
            else ButtonDefaults.ContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = Gray300
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            iconRes?.let {
                Icon(
                    painter = painterResource(id = iconRes),
                    tint = if (enabled) textColor else Gray400,
                    contentDescription = stringResource(R.string.button_icon)
                )
            }
            text?.let {
                Text(
                    text = text,
                    style = Typography.labelLarge,
                    color = if (enabled) textColor else Gray400
                )
            }
        }
    }
}

@Preview
@Composable
private fun MeliButtonPreview() {
    MeliButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.search),
        iconRes = R.drawable.ic_search
    ) { }
}

@Preview
@Composable
private fun MeliButtonNoIconPreview() {
    MeliButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.search)
    ) { }
}

@Preview
@Composable
private fun MeliButtonNoTextPreview() {
    MeliButton(
        modifier = Modifier.fillMaxWidth(),
        iconRes = R.drawable.ic_search
    ) { }
}

@Preview
@Composable
private fun MeliButtonDisabledPreview() {
    MeliButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.search),
        enabled = false
    ) { }
}