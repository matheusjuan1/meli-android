package com.mjdev.meli.data.remote.util

import kotlinx.serialization.json.Json

/**
 * Instância única e global do JSON parser para toda a aplicação.
 */
val AppJson = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
    explicitNulls = false
}