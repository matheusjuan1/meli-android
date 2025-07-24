package com.mjdev.meli.data.remote.model

import kotlinx.serialization.Serializable

/**
 * Representa uma resposta de erro da API.
 *
 * @property code Código de erro fornecido pela API, se disponível.
 * @property message Mensagem de erro fornecida pela API.
 * @property error Código de erro fornecido pela API.
 * @property status Código de status HTTP da resposta.
 * @property cause Lista de causas do erro, se disponíveis.
 */
@Serializable
data class ApiErrorResponse(
    val code: String?,
    val message: String?,
    val error: String?,
    val status: Int?,
    val cause: List<@Serializable String>?
)