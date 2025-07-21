package com.mjdev.meli.ui.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mjdev.meli.ui.theme.Typography

/**
 * MeliTextField é um componente de campo de texto reutilizável com suporte a foco e mudança de valor.
 *
 * @param value O valor atual do campo de texto.
 * @param placeHolder Texto de espaço reservado exibido quando o campo está vazio.
 * @param enabled Indica se o campo de texto está habilitado. Padrão é `true`.
 * @param onValueChange A ação a ser executada quando o valor do campo de texto muda.
 */
@Composable
fun MeliTextField(
    value: String,
    placeHolder: String = "",
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        value = value,
        placeholder = { Text(placeHolder, style = Typography.labelMedium.copy(fontSize = 16.sp)) },
        enabled = enabled,
        textStyle = Typography.bodyLarge,
        onValueChange = {
            onValueChange(it)
        }
    )
}

@Preview
@Composable
private fun MeliTextFieldPreview() {
    MeliTextField(
        value = "",
        placeHolder = "Digite aqui",
        onValueChange = { }
    )
}